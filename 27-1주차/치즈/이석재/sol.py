import sys
from collections import deque

r = sys.stdin.readline
height, width = map(int, r().split())
board = []
ori_sum = 0
for i in range(height):
    list1 = list(map(int, r().split()))
    ori_sum += sum(list1)
    board.append(list1)


def print2(board):
    print("===================")
    for i in range(len(board)):
        print(*board[i])
    print()


def search_cheeses_and_edges():
    visited = [[0] * width for _ in range(height)]

    def bfs(x, y, target):
        visited[x][y] = 1
        sequence = [(x, y)]

        q = deque([(x, y)])
        xpo, ypo = [0, 0, 1, -1], [1, -1, 0, 0]
        while q:
            cur_x, cur_y = q.popleft()
            for i in range(4):
                new_x, new_y = cur_x + xpo[i], cur_y + ypo[i]
                if 0 <= new_x < height and 0 <= new_y < width:
                    if not visited[new_x][new_y]:
                        if board[new_x][new_y] == target:
                            visited[new_x][new_y] = 1
                            q.append((new_x, new_y))
                            sequence.append((new_x, new_y))
        return sequence

    etc = bfs(0, 0, 0)
    cheeses = []
    for i in range(height):
        for j in range(width):
            if not visited[i][j] and board[i][j] == 1:
                cheeses += bfs(i, j, 1)
    return cheeses, etc


def find_cheeses_edge():
    edges_set = set(edges)
    xpo, ypo = [0, 0, 1, -1], [1, -1, 0, 0]
    result = []
    for item in cheeses:
        for i in range(4):
            new_x, new_y = item[0] + xpo[i], item[1] + ypo[i]
            if 0 <= new_x < height and 0 <= new_y < width:
                if (new_x, new_y) in edges_set:
                    result.append(item)
                    break
    return result


def erase_edge():
    count = 0
    for item in erasable_list:
        board[item[0]][item[1]] = 0
        count += 1
    return count


# main
cur_cheese_sum = prev_cheese_sum = ori_sum
time = 0
while True:
    # 1초 증가
    time += 1
    # 치즈와 구멍을 제외한 엣지 목록을 반환함
    cheeses, edges = search_cheeses_and_edges()
    # 치즈목록 줄 지울 치지를 반환함
    erasable_list = find_cheeses_edge()
    # 치즈를 지움
    erased_num = erase_edge()
    # 현재 치즈의 갯수를 체크함
    cur_cheese_sum -= erased_num
    # 현재 치즈의 갯수가 0이거나 0보다 작다면 탈출함
    if cur_cheese_sum <= 0:
        break
    # 이전 치즈의 갯수를 저장함
    prev_cheese_sum = cur_cheese_sum
print(time)
print(prev_cheese_sum)
