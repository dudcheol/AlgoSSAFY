import sys
from collections import deque

r = sys.stdin.readline
height, width = map(int, r().split())
board = []
for i in range(height):
    board.append(list(map(int, r().split())))

board2 = [[0] * width for _ in range(height)]
visited = [[0] * width for _ in range(height)]
island_num = 1
island = {}


def bfs(x, y):
    q = deque([])
    q.append((x, y))
    board2[x][y] = island_num
    island[island_num] = [(x, y)]
    xpo = [0, 0, 1, -1]
    ypo = [1, -1, 0, 0]
    while q:
        cur_x, cur_y = q.popleft()
        for i in range(4):
            new_x, new_y = cur_x + xpo[i], cur_y + ypo[i]
            if 0 <= new_x < height and 0 <= new_y < width:
                if not visited[new_x][new_y] and board[new_x][new_y] == 1:
                    board2[new_x][new_y] = island_num
                    visited[new_x][new_y] = 1
                    island[island_num].append((new_x, new_y))
                    q.append((new_x, new_y))


# 입력받은 2차원 배열을 탐색하면서 1을 만나면 bfs를 통해 섬을 번호순으로 색칠함
for i in range(height):
    for j in range(width):
        if board[i][j] == 1 and not visited[i][j]:
            visited[i][j] = 1
            bfs(i, j)
            island_num += 1

bridge = {}
for land in range(1, island_num):
    bridge[land] = {}


def make_bridge(land_index, land_pos):
    x = [0, 0, 1, -1]
    y = [1, -1, 0, 0]
    for point in land_pos:
        for i in range(4):
            cur_x, cur_y = point
            cur_length = 0
            while True:
                cur_x, cur_y = cur_x + x[i], cur_y + y[i]
                if not (0 <= cur_x < height and 0 <= cur_y < width):
                    break
                if board2[cur_x][cur_y] == land_index:
                    break
                else:
                    if board2[cur_x][cur_y] == 0:
                        cur_length += 1
                    else:
                        if cur_length < 2:
                            break
                        if board2[cur_x][cur_y] not in bridge[land_index]:
                            bridge[land_index][board2[cur_x][cur_y]] = cur_length
                            break
                        else:
                            bridge[land_index][board2[cur_x][cur_y]] = \
                                min(bridge[land_index][board2[cur_x][cur_y]], cur_length)
                            break

# 모든 섬에 대해서 다리를 만들 수 있다면 길이가 2 이상인 최소 길이의 다리를 만듬
for land in island:
    lands = island[land]
    make_bridge(land, lands)

bridges = []
for land in bridge:
    for land2 in bridge[land]:
        bridges.append((bridge[land][land2], land, land2))
# 다리 길이를 오름차순 정렬
bridges.sort()

# kruskal
rank = [0] * island_num
parent = [i for i in range(island_num)]


def find(node):
    if parent[node] == node:
        return parent[node]
    else:
        parent[node] = find(parent[node])
        return parent[node]


def union(src, dsc):
    p1, p2 = find(src), find(dsc)
    if p1 != p2:
        if rank[p1] > rank[p2]:
            parent[p2] = p1
        else:
            parent[p1] = p2
            if rank[p1] == rank[p2]:
                rank[p2] += 1
        return True

# kruskal 을 이용해 mst를 계산해줌
result = 0
for bridge in bridges:
    length, src, dsc = bridge
    if union(src, dsc) is True:
        result += length
# 만일 모든 섬의 부모가 같지 않다면 MST가 만들어지지 않은 것이므로 모든 섬을 잇는 것은 불가능
# 그렇지 않다면 결과 출력
for i in range(1, island_num - 1):
    if find(i) != find(i + 1):
        print(-1)
        break
else:
    print(result)
