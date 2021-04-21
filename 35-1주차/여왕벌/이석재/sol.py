import sys

r = sys.stdin.readline

length, day_length = map(int, r().split())
board = [[1] * length for _ in range(length)]
temp = None
index = None
for i in range(day_length):
    zero_cnt, one_cnt, two_cnt = map(int, r().split())
    if not temp:
        temp = [0] * zero_cnt + [1] * one_cnt + [2] * two_cnt
    else:
        for p in range(zero_cnt, zero_cnt + one_cnt):
            temp[p] += 1
        for p in range(zero_cnt + one_cnt, 2 * length - 1):
            temp[p] += 2

index = 0
for x in range(length - 1, -1, -1):
    board[x][0] += temp[index]
    index += 1
for y in range(1, length):
    board[0][y] += temp[index]
    index += 1

for x in range(1, length):
    for y in range(1, length):
        board[x][y] = board[0][y]

for item in board:
    print(" ".join(map(str, item)))
