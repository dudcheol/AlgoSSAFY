# 결국 구해야 하는 건
# 시작점에서 경유지까지 거리 + 경유지에서 목적지1, 목적지2 까지의 거리가 가장 짧게 되는 경유지를 구하는 것.
# 생각하는덴 좀 걸렸는데
# 풀고나니깐 왜 석재형이 잘 풀 것 같다는지 알겠어서 재밌었음 ㅎㅎ;

def solution(node_num, start_node, target1, target2, fares):
    graph = [[float("inf")] * (node_num + 1) for _ in range(node_num + 1)]
    for fare in fares:
        src, dsc, cost = fare
        graph[src][dsc] = cost
        graph[dsc][src] = cost
    for i in range(1, node_num + 1):
        graph[i][i] = 0
    for p in range(1, node_num + 1):
        for i in range(1, node_num + 1):
            for j in range(1, node_num + 1):
                if graph[i][j] > graph[i][p] + graph[p][j]:
                    graph[i][j] = graph[i][p] + graph[p][j]
    result = float("inf")
    for i in range(1, node_num + 1):
        result = min(result, graph[start_node][i] + graph[i][target1] + graph[i][target2])
    return result
