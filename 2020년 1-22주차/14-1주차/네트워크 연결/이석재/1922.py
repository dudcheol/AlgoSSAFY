import sys

r = sys.stdin.readline
vertex_num = int(r())
edge_num = int(r())
# graph = {}
# for i in range(1, vertex_num + 1):
#     graph[i] = set()
# for _ in range(edge_num):
#     start, end, weight = map(int, r().split())
#     graph[start].add((weight, end))
edges = []
for _ in range(edge_num):
    edges.append(list(map(int, r().split())))
edges.sort(key=lambda x: x[2])


def initt(vertex_num):
    parent = [i for i in range(0, vertex_num + 1)]
    dist = [float("inf")] * (vertex_num + 1)
    level = [0] * (vertex_num + 1)
    return parent, dist, level


parent, dist, level = initt(vertex_num)
# print(parent, dist)

def find(index):
    if parent[index] == index:
        return index
    else:
        parent[index] = find(parent[index])
        return parent[index]


def union(index1, index2):
    p1 = find(index1)
    p2 = find(index2)
    if p1 == p2:
        return False
    if p1 != p2:
        if level[p1] > level[p2]:
            parent[p2] = p1
        else:
            parent[p1] = p2
            if level[p1] == level[p2]:
                p2 += 1
        return True


mst = []
for edge in edges:
    start, end, weight = edge
    if union(start, end) is True:
        mst.append(weight)
print(sum(mst))
