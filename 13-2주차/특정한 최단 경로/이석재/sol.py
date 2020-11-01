import sys
from heapq import *

r = sys.stdin.readline
vertex_num, edges = map(int, r().split())

graph = {}
for i in range(edges):
    start, end, value = map(int, r().split())
    if start not in graph:
        graph[start] = set()
        graph[start].add((value, end))
    else:
        graph[start].add((value, end))
    if end not in graph:
        graph[end] = set()
        graph[end].add((value, start))
    else:
        graph[end].add((value, start))


hub1, hub2 = map(int, r().split())
for i in range(1, vertex_num + 1):
    if i not in graph:
        graph[i] = set()

for vertex in graph:
    print(vertex, graph[vertex])

# 방법은 2가지
# 1 -> hub1 -> hub2 -> dest
# 1 -> hub2 -> hub1 -> dest
# 다익스트라를 통해서 최종 최단거리를 구하고 둘 중 작은것을 구한다
def sol(start, end):
    # print(graph)
    queue = [(0, start)]
    heapify(queue)
    dist = [float("inf")] * (vertex_num + 1)
    dist[start] = 0
    while queue:
        item = heappop(queue)
        cur_dist, cur_vertex = item
        if dist[cur_vertex] < cur_dist:
            continue
        for adj in graph[cur_vertex]:
            adj_dist, adj_vertex = adj
            if dist[adj_vertex] > cur_dist + adj_dist:
                dist[adj_vertex] = cur_dist + adj_dist
                heappush(queue, (cur_dist + adj_dist, adj_vertex))
    # print(dist)
    return dist[end]

# print(graph)
result = min((sol(1, hub1) + sol(hub1, hub2) + sol(hub2, vertex_num)),
             (sol(1, hub2) + sol(hub2, hub1) + sol(hub1, vertex_num)))
# print(result)
print(result if result != float("inf") else -1)
