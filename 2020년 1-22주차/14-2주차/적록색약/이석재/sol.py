import sys
from collections import deque

r = sys.stdin.readline

# R과 G의 유사도를 위해서 R, G 값은 차이가 1이 나게
# B는 2가 나게 바꿔줌
def mapping(item):
    if item == "R":
        return 0
    elif item == "G":
        return 1
    elif item == "B":
        return 3


# 입력받을 배열, 방문처리할 배열, 영역의 사이즈를 담을 배열
length = int(r())
board = []
visited1, visited2 = [], []
count1, count2 = [], []


def bfs(xpo, ypo, flag):
    global visited1, visited2, count1, count2
    x = [0, 0, 1, -1]
    y = [1, -1, 0, 0]
    queue = deque([(xpo, ypo)])
    target = board[xpo][ypo]
    temp = 1
    while queue:
        cur_x, cur_y = queue.popleft()
        for i in range(4):
            new_x, new_y = cur_x + x[i], cur_y + y[i]
            if 0 <= new_x < length and 0 <= new_y < length:
                # 색약이 아닌 경우는 색이 완벽히 동일한 경우만 큐에 넣어줌
                if flag == 1:
                    if not visited1[new_x][new_y] and board[new_x][new_y] == target:
                        visited1[new_x][new_y] = 1
                        queue.append((new_x, new_y))
                        temp += 1 
                # 색약인 경우는 색의 차이가 1이하로 나는 경우만, 즉 R과 G의 비교일 경우에만 큐에 넣어줌
                else:
                    if not visited2[new_x][new_y] and board[new_x][new_y] in [target, target+ 1, target - 1]:
                        visited2[new_x][new_y] = 1
                        queue.append((new_x, new_y))
                        temp += 1
    # 반환된 영역의 길이를 저장해줌
    if flag == 1:
        count1.append(temp)
    else:
        count2.append(temp)


for i in range(length):
    board.append(list(map(lambda x: mapping(x), list(r().rstrip()))))
    visited1.append([0] * length)
    visited2.append([0] * length)

for i in range(length):
    for j in range(length):
        if not visited1[i][j]:
            bfs(i, j, 1)
        if not visited2[i][j]:
            bfs(i, j, 2)

print(len(count1), len(count2))
