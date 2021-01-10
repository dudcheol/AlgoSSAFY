import sys
from itertools import combinations
from collections import deque
from functools import reduce

r = sys.stdin.readline
# 입력단계
node_num = int(r())
# 비용을 저장하는 자료구조
cost = [0] + list(map(int, r().split()))
# 그래프를 저장하는 자료구조
graph = {}
for node in range(1, node_num + 1):
    graph[node] = set()
for node in range(1, node_num + 1):
    length, *nodes = map(int, r().split())
    for item in nodes:
        graph[node].add(item)
# 조합을 사용하기 위해 만든 node index 값이 들어있는 자료구조
nodes = [i for i in range(1, node_num + 1)]
# 초깃값은 무한대
result = float("inf")
# 해당 조합의 방문처리를 하는 집합
visited = set()


# tuple_item 안의 노드만 방문할 수 있는지 체크하는 함수
# bfs 하면서 방문한 노드의 갯수를 반환함
def bfs(tuple_item):
    queue = deque([tuple_item[0]])
    visited = {}
    visited[tuple_item[0]] = 1
    while queue:
        cur = queue.popleft()
        for node in graph[cur]:
            if node not in visited and node in tuple_item:
                queue.append(node)
                visited[node] = 1
    return len(visited)

# 전체 노드 집합 == 현재 조합에서 걸린 노드 집합 + 현재 조합의 노드를 제외한 나머지 노드의 집합
for size in range(1, node_num):
    for comb in combinations(nodes, size):
        if comb in visited:
            continue
        comb2 = tuple(filter(lambda x: x not in comb, nodes))
        a, b = bfs(comb), bfs(comb2)
        # bfs 결과가 현재 조합의 node 목록의 길이와 같다는 것은
        # 유효한 케이스라는 뜻
        if a == len(comb) and b == len(comb2):
            a, b = reduce(lambda acc, cur: acc + cost[cur], comb, 0), \
                   reduce(lambda acc, cur: acc + cost[cur], comb2, 0)
            result = min(result, abs(a - b))
        # 동일한 케이스 탐색을 방지하기 위해 방문처리 함
        visited.add(comb)
        visited.add(comb2)

print(result if result != float("inf") else -1)
