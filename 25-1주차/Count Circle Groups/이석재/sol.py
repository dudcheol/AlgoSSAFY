import sys
from collections import deque
from itertools import combinations
import math

r = sys.stdin.readline
test_case = int(r())
for t in range(test_case):
    node_num = int(r())
    # 이걸 DICT으로 쓰면 시간초과남, hash 기반 자료구조는 꼭 안써도되면 쓰지 말자...
    board = [0]*(node_num+1)
    node_index = 1
    for i in range(node_num):
        x, y, length = map(int, r().split())
        board[node_index] = (x, y, length)
        node_index += 1

    roads = []
    parent = [i for i in range(node_num + 1)]
    rank = [0] * (node_num + 1)


    def is_possible(p1, p2):
        x1, y1, length1 = board[p1]
        x2, y2, length2 = board[p2]
        #gap = length1 + length2 - math.sqrt(pow(x1 - x2, 2) + pow(y1 - y2, 2))
        #비용이 큰 실수연산을 막기 위해서 정수로만 연산
        gap = (length1 + length2)**2 - (x1 - x2)**2 - (y1 - y2)**2
        if gap < 0:
            return -1
        else:
            return gap


    def find(node):
        # print(node)
        if parent[node] != node:
            parent[node] = find(parent[node])
        return parent[node]


    def union(node1, node2):
        p1, p2 = find(node1), find(node2)
        if p1 != p2:
            if rank[p1] > rank[p2]:
                parent[p2] = p1
            else:
                parent[p1] = p2
                if rank[p2] == rank[p1]:
                    rank[p2] += 1


    def make_road():
        for comb in combinations([i for i in range(1, node_index)], 2):
            p1, p2 = comb
            t = is_possible(p1, p2)
            if t >= 0:
                union(p1, p2)
    make_road()
    for node in range(1, node_index):
        find(node)
    print(len(set(parent)) - 1)
