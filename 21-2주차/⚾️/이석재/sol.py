# 이 문제를 python3로 통과한 사람은 20.12.27 기준 0명임
# base를 관리할 때 자료구조의 popleft, append를 쓰지말고 변수만을 이용해서 조작하고 마지막 위치만 관리한다
# 자료구조의 연산이 TLE의 원인임.

import sys
from itertools import permutations

r = sys.stdin.readline


def sol(inning, index):
    global temp_score
    out_count, base1, base2, base3 = 0, 0, 0, 0
    while out_count < 3:
        if inning[order[index]] == 0:
            out_count += 1
        elif inning[order[index]] == 1:
            temp_score += base3
            base3, base2, base1 = base2, base1, 1
        elif inning[order[index]] == 2:
            temp_score += (base3 + base2)
            base3, base2, base1 = base1, 1, 0
        elif inning[order[index]] == 3:
            temp_score += (base3 + base2 + base1)
            base3, base2, base1 = 1, 0, 0
        elif inning[order[index]] == 4:
            temp_score += (base3 + base2 + base1 + 1)
            base3, base2, base1 = 0, 0, 0
        index = (index + 1) % 9
    return index


inning = int(r())
board = []
for i in range(inning):
    board.append(list(map(int, r().split())))

result = 0
for order in permutations(range(1, 9), 8):
    order = [*order[:3], 0, *order[3:]]
    # print(order)
    cur_index, temp_score = 0, 0
    for inning in board:
        cur_index = sol(inning, cur_index)
    result = max(result, temp_score)
print(result)
