import sys

r = sys.stdin.readline
# 재귀 depth를 늘리는 조작을 주석처리할 경우에는 메모리초과가 나지 않음...
# Python은 AI할때 쓰세요 제발 ㅠㅠ
# sys.setrecursionlimit(10 ** 9)

node_num, command_length = map(int, r().split())

parents = [i for i in range(0, node_num + 1)]
rank = [0] * (node_num + 1)


def find(node):
    if node == parents[node]:
        return node
    else:
        parents[node] = find(parents[node])
        return parents[node]


def union(node1, node2):
    parent1, parent2 = find(node1), find(node2)

    if parent1 != parent2:
        if rank[parent1] > rank[parent2]:
            parents[parent2] = parent1
        else:
            parents[parent1] = parent2
            if rank[parent1] == rank[parent2]:
                rank[parent2] += 1


for _ in range(command_length):
    flag, n1, n2 = map(int, r().split())
    if flag == 0:
        union(n1, n2)
    elif flag == 1:
        print("NO" if find(n1) != find(n2) else "YES")
