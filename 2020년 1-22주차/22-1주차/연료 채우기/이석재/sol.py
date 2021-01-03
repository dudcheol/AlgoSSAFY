# 현재 위치 이전에 있는 주유소를 우선순위큐에 넣고
# 가장 연료를 많이 충전할 수 있는 주유소를 뽑아내고 위치를 갱신하는 것을 반복한다

import sys
from heapq import *
from collections import deque

r = sys.stdin.readline

car_num = int(r())
cars = []
for i in range(car_num):
    cars.append(list(map(int, r().split())))
cars.sort()
cars = deque(cars)

target, cur_fuel = map(int, r().split())
cur_pos = 0
result = 0
cur_pos += cur_fuel
pq = []
index = 0
while cur_pos < target:
    # queue에 중복된 값을 넣어주지 않기 위해서 deque로 처리해버림
    while cars and cars[0][0] <= cur_pos:
        heappush(pq, -cars.popleft()[1])
    if not pq:
        result = -1
        break
    else:
        cur = heappop(pq)
        # print(-cur)
        cur_pos += (-cur)
        result += 1

print(result)
