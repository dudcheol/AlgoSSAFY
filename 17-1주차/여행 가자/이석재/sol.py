import sys

r = sys.stdin.readline
vertex_num = int(r())
command_length = int(r())
board = [[0] * (vertex_num + 1)]
for i in range(vertex_num):
    board.append([0] + list(map(int, r().split())))
commands = list(map(int, r().split()))

# 플로이드 워셜
for hub in range(1, vertex_num + 1):
    for start in range(1, vertex_num + 1):
        for end in range(1, vertex_num + 1):
            if board[start][hub] and board[hub][end]:
                board[start][end] = 1

src = commands[0]
for des in commands[1:]:
    # 출발지와 도착지가 같거나 출발지에서 도착지로 길이 있으면 전진
    if src == des or board[src][des]:
        src = des
    # 전진할 수 없으면 반복문 탈출
    else:
        print("NO")
        break
# for 문을 중간에 탈출하지 않고 다 돌았다면, 즉 모든 여행 일정이 소화가 가능하다면 
else:
    print("YES")
