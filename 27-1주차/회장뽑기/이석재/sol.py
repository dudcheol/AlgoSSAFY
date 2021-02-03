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
# 입력 조건에서 동일한 사람이 친구관계라고 들어올 수도 있기 때문에 0을 부여
for i in range(1, node_num + 1):
    dist[i][i] = 0
# floyd_warshall
for p in range(1, node_num + 1):
    for i in range(1, node_num + 1):
        for j in range(1, node_num + 1):
            dist[i][j] = min(dist[i][p] + dist[p][j], dist[i][j])
# 플로이드의 결과를 훝으면서 회원의 가장 큰 정수값을 찾음
# 그 값이 점수고, 가장 작은 값을 가지는 회원들의 모음을 저장해 반환
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
print(*result)
