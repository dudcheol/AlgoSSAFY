import sys
from collections import Counter

r = sys.stdin.readline

height, width = map(int, r().split())
board = []

for i in range(height):
    list1 = list(r().rstrip())
    board.append(list1)
commands = list(map(int, list(r().rstrip())))
curx, cury = 0, 0
mad = []
for i in range(height):
    for j in range(width):
        if board[i][j] == "I":
            curx, cury = i, j
            board[i][j] = "."
        elif board[i][j] == "R":
            mad.append((i, j))
            board[i][j] = "."
# print(mad)
x = [-1, 1, 1, 1, 0, 0, 0, -1, -1, -1]
y = [-1, -1, 0, 1, -1, 0, 1, -1, 0, 1]


def cal_dist(x1, y1, x2, y2):
    return abs(x1 - x2) + abs(y1 - y2)


def move():
    global mad
    temp = []
    # print(curx, cury)
    for i in range(len(mad)):

        axpo, aypo = mad[i]
        # print(axpo,aypo)
        tx, ty = 0, 0
        cur_dist = float("inf")
        for dirr in range(1, 10):
            # print(axpo + x[dirr], aypo + y[dirr])
            if 0 <= axpo + x[dirr] < height and 0 <= aypo + y[dirr] < width:
                if cal_dist(curx, cury, axpo + x[dirr], aypo + y[dirr]) < cur_dist:
                    cur_dist = cal_dist(curx, cury, axpo + x[dirr], aypo + y[dirr])
                    tx, ty = axpo + x[dirr], aypo + y[dirr]
                    # print(tx, ty)
        # print("==================")
        # print(curx, cury)
        # print(tx, ty)
        if tx==curx and ty==cury:
            return False
        temp.append((tx, ty))
        # print(temp)
    # print(temp)
    t = Counter(temp)
    temp2 = []
    for item in t:
        if t[item] == 1:
            temp2.append(item)

    mad = temp2
    # print(mad)


# print(mad)
time = 0
for command in commands:
    # print("=============")
    # print(curx, cury)
    time += 1
    curx, cury = curx + x[command], cury + y[command]
    if (curx, cury) in mad:
        print(f"kraj {time}")
        break
    else:
        if move() is False:
            print(f"kraj {time}")
            break
    # print(mad)
# print(mad)
# print(curx,cury)
else:
    board[curx][cury] = "I"
    for item in mad:
        board[item[0]][item[1]] = "R"

    for item in board:
        print("".join(item))
