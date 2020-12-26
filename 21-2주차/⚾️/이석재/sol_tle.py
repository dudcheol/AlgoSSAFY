# 예전에 시간초과난 코드
# 구현은 정확히 했음
# move 함수에서 deque를 사용해 이닝을 관리하는 부분에서 TLE가 걸림

import sys
from itertools import permutations
from collections import deque

r = sys.stdin.readline


def move(batter_number, action):
    global mount, out_count, cur_score
    if action in [1, 2, 3, 4]:
        mount[-1] = batter_number
        for p in range(action):
            lefted_batter = mount.popleft()
            if lefted_batter > -1:
                cur_score += 1
            mount.append(-1)
    elif action == 0:
        out_count += 1


inning = int(r())
board = []
for i in range(inning):
    board.append(list(map(int, r().split())))
board_zip = list(zip(*board))
first = board_zip[0]
lefted = board_zip[1:]
tot_result = 0
permu_list = list(set(list(permutations(lefted, 8))))
for item in permu_list:
    temp = list(item)
    cur_list = temp[:3] + [first] + temp[3:]
    cur_index = 0
    cur_score = 0
    # print("순열 시작")
    for i in range(inning):
        # print(f"{i} 이닝 시작")
        mount = deque([-1] * 4)
        out_count = 0
        while out_count < 3:
            for j in range(cur_index, 9):
                cur_index = j
                move(j, cur_list[j][i])
                if out_count == 3:
                    cur_index = j + 1
                    if cur_index == 9:
                        cur_index = 0
                    break
            else:
                cur_index = 0
    if cur_score > tot_result:
        tot_result = cur_score
print(tot_result)
