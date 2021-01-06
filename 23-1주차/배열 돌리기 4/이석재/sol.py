import sys
from collections import deque
from itertools import permutations
from copy import deepcopy

r = sys.stdin.readline
height, width, command_length = map(int, r().split())

board_ori = []
for i in range(height):
    board_ori.append(list(map(int, r().split())))
commandss = []
for i in range(command_length):
    commandss.append(list(map(int, r().split())))


def sol(cent_x, cent_y, len1):
    global board
    for t in range(1, len1 + 1):
        points = []
        vals = []
        cur_x, cur_y = cent_x - t, cent_y - t
        length = 2 * t
        for i in range(length):
            points.append((cur_x, cur_y))
            vals.append(board[cur_x][cur_y])
            cur_y += 1
        for i in range(length):
            points.append((cur_x, cur_y))
            vals.append(board[cur_x][cur_y])
            cur_x += 1
        for i in range(length):
            points.append((cur_x, cur_y))
            vals.append(board[cur_x][cur_y])
            cur_y -= 1
        for i in range(length):
            points.append((cur_x, cur_y))
            vals.append(board[cur_x][cur_y])
            cur_x -= 1
        # points = deque(points)
        vals = deque(vals)
        # points.appendleft(points.pop())
        vals.appendleft(vals.pop())
        # print(points)
        # print(vals)
        for i in range(len(vals)):
            board[points[i][0]][points[i][1]] = vals[i]


result = float("inf")
for commands in permutations(commandss, len(commandss)):
    board = deepcopy(board_ori)
    for command in commands:
        # print(command)
        cent_x, cent_y, length = command
        sol(cent_x - 1, cent_y - 1, length)
    result = min(result, min(sum(i) for i in board))
print(result)
