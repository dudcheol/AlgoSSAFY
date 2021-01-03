# 삽질 많이 함

import sys
from collections import deque

r = sys.stdin.readline
width, height = map(int, r().split())
board = []
for i in range(height):
    board.append(list(r().rstrip()))

points = []
for i in range(height):
    for j in range(width):
        if board[i][j] == "C":
            points.append((i, j))

visited = [[float("inf")] * width for _ in range(height)]
sx, sy = points[0]
visited[sx][sy] = 0
tx, ty = points[1]
queue = deque([])
x = [0, 0, 1, -1]
y = [1, -1, 0, 0]
for i in range(4):
    newx, newy = sx + x[i], sy + y[i]
    if 0 <= newx < height and 0 <= newy < width:
        if board[newx][newy] != "*":
            queue.append((newx, newy, 0, i))
            visited[newx][newy] = 0

while queue:
    # print(queue)
    cx, cy, cr, cdir = queue.popleft()
    for i in range(4):
        newx, newy = cx + x[i], cy + y[i]
        newr = cr + (1 if cdir != i else 0)
        if 0 <= newx < height and 0 <= newy < width:
            if board[newx][newy] != "*" and visited[newx][newy] >= newr:
                queue.append((newx, newy, newr, i))
                visited[newx][newy] = newr
# for item in visited:
#     print(item)
print(visited[tx][ty])
