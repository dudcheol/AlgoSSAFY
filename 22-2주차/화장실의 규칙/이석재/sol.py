# 줄을 queue로 관리하고 다음 순서를 priority queue로 처리한다
# 단 여기서 pq로 처리할때 매번 줄의 앞 사람을 pq에 넣어주고 빼내면 시간 초과가 난다
# pq에 한번 넣은 사람은 다시 넣지말고, 그 상태에서 새로 추가되는 사람만 pq에 넣어줘야 한다.

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

while not board2[-1]:
    board2.pop()
result = 0
pq = []
for deque in board2:
    if deque:
        pq.append(deque.popleft())
heapify(pq)
while True:
    target = heappop(pq)
    if target[3] == 1:
        break
    else:
        result += 1
        if board2[target[2]]:
            heappush(pq, board2[target[2]].popleft())
print(result)
