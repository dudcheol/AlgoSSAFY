import sys
from heapq import *

r = sys.stdin.readline


def clean(pq):
    while pq and visited[pq[0][1]] == 0:
        heappop(pq)


test_case = int(r())
for t in range(test_case):
    length = int(r())
    max_pq, min_pq = [], []
    visited = [0] * 1000000
    for i in range(length):
        commands = r().split()
        if commands[0] == "I":
            heappush(min_pq, (int(commands[1]), i))
            heappush(max_pq, (-int(commands[1]), i))
            visited[i] = 1

        elif commands[0] == "D":
            if commands[1] == "1":
                clean(max_pq)
                if max_pq:
                    visited[max_pq[0][1]] = 0
                    heappop(max_pq)
            else:
                clean(min_pq)
                if min_pq:
                    visited[min_pq[0][1]] = 0
                    heappop(min_pq)
    clean(max_pq)
    clean(min_pq)
    if not max_pq:
        print("EMPTY")
    else:
        print(-max_pq[0][0], min_pq[0][0])
