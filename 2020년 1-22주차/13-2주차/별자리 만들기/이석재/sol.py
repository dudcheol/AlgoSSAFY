import sys
from math import sqrt
from heapq import *

r = sys.stdin.readline


def dist(p1, p2):
    return sqrt(pow(p1[0] - p2[0], 2) + pow(p1[1] - p2[1], 2))


length = int(r())
board = []
for i in range(length):
    board.append(tuple(map(float, r().split())))
# print(board)
distance_map = []
for point1 in board:
    list1 = []
    for point2 in board:
        if point1 == point2:
            list1.append(0)
        else:
            list1.append(dist(point1, point2))
    distance_map.append(list1)
# print(distance_map)
visited = [0] * length
distance = [float("inf")] * length
distance[0] = 0
for i in range(length):
    queue = []
    heapify(queue)
    for j in range(length):
        if visited[j] == 0:
            heappush(queue, (distance[j], j))
    item = heappop(queue)
    visited[item[1]] = 1
    for p in range(length):
        if visited[p] == 0:
            if distance[p] > distance_map[item[1]][p]:
                distance[p] = distance_map[item[1]][p]
print(round(sum(distance), 2))
