import sys
from collections import deque
from copy import deepcopy

r = sys.stdin.readline

length = int(r())
graph = {}
weights = [0] * (length + 1)
cache = [0] * (length + 1)

indegree = [0] * (length + 1)
graph = {}

# 그래프 문제에서 노드에 대한 결과를 담는 그릇은 웬만하면 처음에 초기화하고 입력받자
for i in range(1, length + 1):
    graph[i] = set()

for i in range(1, length + 1):
    weight, *pre_list = r().split()
    weight = int(weight)
    pre_list = list(map(int, pre_list))
    weights[i] = weight
    for item in pre_list:
        if item == -1:
            continue
        else:
            graph[item].add(i)
            indegree[i] += 1

result = deepcopy(weights)
queue = deque([])
for i in range(1, length + 1):
    if indegree[i] == 0:
        queue.append(i)
visited = []
while queue:
    cur = queue.popleft()
    visited.append(cur)
    # 결국 큐에서 팝될때가 최종 시간이 결정됨
    # 최종 시간은 자신을 만드는 시간 + 캐시에 담겨있는 시간
    result[cur] = weights[cur] + cache[cur]
    for item in graph[cur]:
        indegree[item] -= 1
        # 캐시 값은 현재 노드의 최종 시간 vs 기존에 캐시에 저장되어 있는 값 중 큰 것
        # 왜냐면 캐시에 20분이 더해져있음, 그런데 현재 indegree 처리를 해주는 부모 노드의 최종시간이 30분이면
        # 캐시는 30분으로 바뀌어야 하기 떄문
        cache[item] = max(result[cur], cache[item])
        if indegree[item] == 0:
            queue.append(item)
for item in result[1:]:
    print(item)
