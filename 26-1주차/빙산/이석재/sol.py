import sys
from collections import deque

r = sys.stdin.readline


def print2(board):
    print("===============")
    for item in board:
        print(*item)


height, width = map(int, r().split())
board = []
for i in range(height):
    board.append(list(map(int, r().split())))


def melt2(xpo, ypo):
    x = [1, -1, 0, 0]
    y = [0, 0, 1, -1]
    cnt = 0
    for i in range(4):
        nx, ny = xpo + x[i], ypo + y[i]
        if 0 <= nx < height and 0 <= ny < width:
            if board[nx][ny] == 0:
                cnt += 1
    return cnt


def melt():
    cache = [[0] * width for _ in range(height)]
    for i in range(height):
        for j in range(width):
            cur = board[i][j]
            if cur != 0:
                cache[i][j] = board[i][j] - melt2(i, j)
                if cache[i][j] < 0:
                    cache[i][j] = 0
    for i in range(height):
        for j in range(width):
            board[i][j] = cache[i][j]


def check():
    visited = [[0] * width for _ in range(height)]
    x = [1, -1, 0, 0]
    y = [0, 0, 1, -1]
    cnt = 0
    tot_ice = 0

    def bfs(xpo, ypo):
        q = deque([(xpo, ypo)])
        visited[xpo][ypo] = 1
        ice = board[xpo][ypo]
        while q:
            cur_x, cur_y = q.popleft()
            for p in range(4):
                nx, ny = cur_x + x[p], cur_y + y[p]
                if 0 <= nx < height and 0 <= ny < width:
                    if not visited[nx][ny] and board[nx][ny] != 0:
                        visited[nx][ny] = 1
                        q.append((nx, ny))
                        ice += board[nx][ny]
        return ice

    for i in range(height):
        for j in range(width):
            if not visited[i][j] and board[i][j] != 0:
                tot_ice += bfs(i, j)
                cnt += 1
    # print(cnt)
    return [cnt, tot_ice]


cnt = 0
while True:
    cnt += 1
    melt()
    cntt, tot_ice = check()
    if tot_ice != 0 and cntt >= 2:
        break
    elif tot_ice == 0:
        cnt = 0
        break
print(cnt)
