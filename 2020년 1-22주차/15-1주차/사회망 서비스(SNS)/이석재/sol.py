# http://www.secmem.org/blog/2019/02/10/TreeDP/
# 방문처리, 엣지관리하는 자료구조를 모두 set에서 list로 변환해서 메모리 초과 탈출
# 현재 노드가 얼리어댑터일때의 dp 값을 제대로 고려해줘야함
# 현재 노드가 일반유저면 상위노드는 반드시 얼리어댑터가 되어야함
# 하지만 현재 노드가 얼리어댑터라면 상위노드 또한 얼리어댑터가 가능함
# 그러므로 dp[현재노드][얼리어답터] = min ( dp[이전노드들][얼리어답터], dp[이전노드들][얼리어답터아님) + 1 
# 이런식으로 되어야함
# 예상해보건데 dfs를 재귀로 함수로 호출하게 되면 stack 공간이 모자르게 됨
# 스택 자료구조를 사용해서 구현하면 pypy에서도 통과가 가능할것같음(현재 pypy 통과자 20.11.10 기준 4명)
import sys

sys.setrecursionlimit(10 ** 9)

r = sys.stdin.readline
length = int(r())
graph = {}

for i in range(1, length + 1):
    graph[i] = []
for i in range(length - 1):
    start, end = map(int, r().split())
    graph[start].append(end)
    graph[end].append(start)

visited = [0] * (length + 1)
dp = [[0] * 2 for _ in range(length + 1)]


def dfs(node_index):
    visited[node_index]=1
    flag = 0
    for vertex in graph[node_index]:
        if not visited[vertex]:
            flag = 1
            dfs(vertex)
            dp[node_index][0] += dp[vertex][1]
            dp[node_index][1] += min(dp[vertex][1], dp[vertex][0])
    dp[node_index][1] += 1
    if flag == 0:
        dp[node_index][0] = 0
        dp[node_index][1] = 1


dfs(1)
print(min(dp[1]))
