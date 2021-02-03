import sys

r = sys.stdin.readline

node_num = int(r())
board = [[0] * (node_num + 1) for _ in range(node_num + 1)]
dist = [[float("inf")] * (node_num + 1) for _ in range(node_num + 1)]
while True:
    src, dsc = map(int, r().split())
    if src == dsc == -1:
        break
    board[src][dsc] = board[dsc][src] = 1
    dist[src][dsc] = dist[dsc][src] = 1
for i in range(1, node_num + 1):
    dist[i][i] = 0
for p in range(1, node_num + 1):
    for i in range(1, node_num + 1):
        for j in range(1, node_num + 1):
            dist[i][j] = min(dist[i][p] + dist[p][j], dist[i][j])

score = float("inf")
result = []
for i in range(1, node_num + 1):
    cur_score = max(dist[i][1:])
    if cur_score == score:
        result.append(i)
    else:
        if cur_score < score:
            result = [i]
            score = cur_score
print(score, len(result))
print(*sorted(result))
