# 특정 시점에서 pq의 길이가 3이라고 가정하면
# 그 pq안의 seat 값이 1,2,3 이라는 보장을 할 수 없다
# 다시 말해서 pq의 길이는 3이라 할지라도 그 좌석이 1,2,3 이아니라 1,2,4일수가 있기 때문에
# 의자를 따로 관리해아한다


import sys
from heapq import *
from collections import deque

r = sys.stdin.readline
length = int(r())
board = []
for i in range(length):
    board.append(list(map(int, r().split())))
board.sort(key=lambda x: (x[0], x[1]))
board = deque(board)
visited = {}
pq = []
left = [1]
max_size = 1
while board:
    start, end = board.popleft()
    seat = None
    if not pq:
        seat = heappop(left)
        heappush(pq, (end, seat))
    elif pq[0][0] >= start:
        if not left:
            max_size += 1
            seat = max_size
            heappush(pq, (end, max_size))
        else:
            seat = heappop(left)
            heappush(pq, (end, seat))
    else:
        cur_time, cur_seat = None, None
        while pq and pq[0][0] < start:
            cur_time, cur_seat = heappop(pq)
            heappush(left, cur_seat)
        if not left:
            max_size += 1
            seat = max_size
            heappush(pq, (end, max_size))
        else:
            seat = heappop(left)
            heappush(pq, (end, seat))
    if seat not in visited:
        visited[seat] = 1
    else:
        visited[seat] += 1
print(max_size)
for i in range(1, max_size + 1):
    print(visited[i], end=" ")
