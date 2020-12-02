# https://cs.stackexchange.com/questions/77026/multiple-knapsack-using-dynamic-programming
# 나는 이것도 한번에 못풀었음
import sys
from heapq import *
from collections import deque

r = sys.stdin.readline

stone_num, bag_num = map(int, r().split())
stones = []
for i in range(stone_num):
    w, v = map(int, r().split())
    stones.append((w, v))
bags = []
for i in range(bag_num):
    bags.append(int(r()))
bags.sort()
stones = deque(sorted(stones))
pq = []
result = 0
for bag in bags:
    while stones and stones[0][0] <= bag:
        s = stones.popleft()
        heappush(pq, (-s[1], s[0]))
    result += (-heappop(pq)[0]) if pq else 0
print(result)
