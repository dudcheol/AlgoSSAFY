import sys

r = sys.stdin.readline
vertex_num, edge_num = map(int, r().split())
edges = []
for _ in range(edge_num):
    edges.append(list(map(int, r().split())))

upper = [float("inf")] * (vertex_num + 1)
upper[1] = 0
for _ in range(vertex_num - 1):
    for edge in edges:
        start, end, weight = edge
        if upper[end] > upper[start] + weight:
            upper[end] = upper[start] + weight
for edge in edges:
    start, end, weight = edge
    if upper[end] > upper[start] + weight:
        print(-1)
        break
else:
    for item in upper[2:]:
        print(item if item != float("inf") else -1)
