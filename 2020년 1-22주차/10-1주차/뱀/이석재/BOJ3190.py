import sys
from collections import deque

r = sys.stdin.readline

# dir_dict[현재방향][시계방향, 시계반대방향] = 결과방향
dir_dict = {1: {0: 3, 1: 2}, 2: {0: 1, 1: 0}, 3: {0: 0, 1: 1}, 0: {0: 2, 1: 3}}
# x증감량, y증감량 = dir_dict[현재방향]
dir_dict2 = {2: (-1, 0), 1: (0, 1), 0: (0, -1), 3: (1, 0)}

length = int(r())
apple_num = int(r())
apple_list = []
apple_set = set()
board = [[0] * length for _ in range(length)]
for i in range(apple_num):
    xpo, ypo = list(map(lambda x: x - 1, list(map(int, r().split()))))
    apple_list.append((xpo, ypo))
    apple_set.add((xpo, ypo))
    board[xpo][ypo] = 2
command_num = int(r())
command_list = []
for i in range(command_num):
    time, direction = r().split()
    command_list.append((int(time), 0 if direction == "D" else 1))

#   2
# 0   1
#   3
# D: 시계방향 90도 L: 시계 반대 방향 90도
# 시계 방향 0 시계 반대방향 1
tot_time = 0
tot_length = 1
flag = 0

sx = sy = 0
sdir = 1

snake_list = deque([[sx, sy]])


def reset():
    for i in range(length):
        for j in range(length):
            board[i][j] = 0
    for item in apple_set:
        board[item[0]][item[1]] = 2


def set_snake():
    for item in snake_list:
        board[item[0]][item[1]] = -1


def move(target_time):
    global tot_length, tot_time, flag, sx, sy, sdir, board, snake_list, apple_list, apple_set

    cur_x, cur_y, cur_dir = sx, sy, sdir
    next_x, next_y, next_dir = cur_x, cur_y, cur_dir
    while tot_time < target_time:
        reset()
        set_snake()
        # print(f"{tot_time}초 상황")
        # print(snake_list)
        # for item in board:
        #     print(item)
        # print()
        tot_time += 1
        cur_x, cur_y, cur_dir = next_x, next_y, next_dir

        next_x += dir_dict2[next_dir][0]
        next_y += dir_dict2[next_dir][1]

        # 만약에 머리 위치가 벽이면 반복 종료, 그렇지 않다면
        if next_x < 0 or next_x >= length or next_y < 0 or next_y >= length or board[next_x][next_y] == -1:
            flag = 1
            return
        else:
            # 그 위치에 사과가 있으면
            if board[next_x][next_y] == 2:
                # 사이즈 1 증가
                tot_length += 1
                snake_list.append([next_x, next_y])
                apple_set.remove((next_x, next_y))
            else:
                snake_list.popleft()
                snake_list.append([next_x, next_y])
    sx, sy = next_x, next_y


for command in command_list:
    target_time, direction = command
    move(target_time)
    if flag == 1:
        break
    sdir = dir_dict[sdir][direction]
if flag == 0:
    while True:
        move(10001)
        if flag == 1:
            break
print(tot_time)
