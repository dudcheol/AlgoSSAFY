import sys
from collections import deque
from heapq import *

r = sys.stdin.readline
length, line_num, index = map(int, r().split())

board = deque([])
board2 = []
for i in range(line_num):
    board2.append(deque([]))
index2 = 0
for i in range(length):
    temp = tuple(map(int, r().split()))
    if i == index:
        board2[index2].append((-temp[0], -temp[1], index2, 1))
    else:
        board2[index2].append((-temp[0], -temp[1], index2, 0))
    index2 = (index2 + 1) % line_num
# 와 메모리 주소 공유되네;;;


result = 0
while True:
    # for item in board2:
    #     print(item)
    temp = []
    for deque in board2:
        if deque:
            temp.append(deque[0])
    heapify(temp)

    # temp.sort(key=lambda x: (-x[0], -x[1], x[2]))
    # print(temp)
    target = temp[0]
    if target[3] == 1:
        break
    else:
        result += 1
        board2[target[2]].popleft()
print(result)
