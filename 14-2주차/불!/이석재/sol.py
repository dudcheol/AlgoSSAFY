import sys
from collections import deque

r = sys.stdin.readline

height, width = map(int, r().split())
# 배열 자체를 주어진 행렬값보다 2를 더해주고 시작함
# 이렇게 하면 bfs 처리할 때 기존 배열의 바깥으로 나갔을 경우를 배열의 index out of range 에러를
# 고려하지 않고 해결할 수 있음
height_padding, width_padding = height + 2, width + 2


def mapper(item):
    if item == "#":
        return 1
    elif item == ".":
        return 0
    elif item == "J":
        return 2
    elif item == "F":
        return 3


board = [[-1] * width_padding]
visited = [[0] * width_padding for _ in range(height_padding)]
for i in range(height):
    board.append([-1] + list(map(lambda x: mapper(x), list(r().rstrip()))) + [-1])
board.append([-1] * width_padding)

startx = starty = 0
fire_list = []
for i in range(height_padding):
    for j in range(width_padding):
        if board[i][j] == 2:
            startx, starty = i, j
        elif board[i][j] == 3:
            fire_list.append((i, j, 1, 0))

# 0 빈공간 1 벽 2 지훈이 3 불
# 큐에 사람을 먼저 넣어주면 사람이 이동한 자리에 불이 붙는 경우가 생김
# 즉 유효하지 않은 값이 생김
# 해결하는 방법은 크게 2가지
# 아래 코드 처럼 유효하지 않은 경우를 처리하기
# 그냥 불 먼저 처리하기, 즉 큐에 불을 먼저넣고, 사람을 넣은 뒤에 bfs
queue = [(startx, starty, 0, 0)] + fire_list
queue = deque(queue)
board[startx][starty] = 0
result = 0
x = [0, 0, 1, -1]
y = [1, -1, 0, 0]
result = 0
while queue:
    cur_x, cur_y, cur_type, cur_length = queue.popleft()
    # 위에서 말한 유효하지 않은 경우, 즉 현재 사람을 처리하는데
    # 사람의 위치가 불이라면 그냥 continue로 지나가줌
    if cur_type == 0 and board[cur_x][cur_y] == 3:
        continue
    # 기존에 주어진 행렬의 사이즈를 넘어버린 좌표 -> 탈출을 성공한 
    if not (1 <= cur_x < height + 1 and 1 <= cur_y < width + 1):
        result = cur_length
        break
    for i in range(4):
        new_x, new_y = cur_x + x[i], cur_y + y[i]
        if 0 <= new_x < height_padding and 0 <= new_y < width_padding:
            if cur_type == 0:
                if visited[new_x][new_y] == 0 and board[new_x][new_y] in [0, -1]:
                    visited[new_x][new_y] = 1
                    queue.append((new_x, new_y, cur_type, cur_length + 1))
            elif cur_type == 1:
                if board[new_x][new_y] == 0:
                    board[new_x][new_y] = 3
                    queue.append((new_x, new_y, cur_type, cur_length + 1))

print(result if result != 0 else "IMPOSSIBLE")
