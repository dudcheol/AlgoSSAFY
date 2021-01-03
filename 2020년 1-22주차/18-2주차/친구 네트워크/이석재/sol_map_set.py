# 메모리 초과
# 아무래도 set 을 모든 노드에 대해서 복사하고 가지는 작업이 무거워서 

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

        if p1 not in networks and p2 not in networks:
            new_set = set()
            new_set.add(p1)
            new_set.add(p2)
            networks[p1] = new_set
            networks[p2] = new_set
            print(2)
        elif p1 in networks and p2 in networks:
            new_set = networks[p1].union(networks[p2])
            networks[p1] = new_set
            networks[p2] = new_set
            print(len(new_set))
        else:
            if p1 in networks:
                networks[p1].add(p2)
                networks[p2] = networks[p1]
                print(len(networks[p1]))
            elif p2 in networks:
                networks[p2].add(p1)
                networks[p1] = networks[p2]
                print(len(networks[p2]))
