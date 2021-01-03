# 시간 초과
# 아무래도 그래프를 업데이트하면서 매번 BFS를 호출해서 탐색을 하다보니 시간이 부족한듯

import sys
from collections import deque

r = sys.stdin.readline


def bfs(start, networks):
    queue = deque([start])
    visited = set()
    visited.add(start)
    count = 1
    while queue:
        cur = queue.popleft()
        for friend in networks[cur]:
            if friend not in visited:
                queue.append(friend)
                visited.add(friend)
                count += 1
    return count


test_case_num = int(r())
for _ in range(test_case_num):
    length = int(r())
    networks = {}
    for i in range(length):
        p1, p2 = r().split()
        if p1 not in networks:
            networks[p1] = set()
        networks[p1].add(p2)
        if p2 not in networks:
            networks[p2] = set()
        networks[p2].add(p1)
        print(bfs(p1, networks))
