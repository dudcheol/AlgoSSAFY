# https://justicehui.github.io/medium-algorithm/2019/01/01/monotoneStack/
# 이것도 못품
# https://justicehui.github.io/ps/2019/07/05/BOJ17298-BOJ17299/
import sys

r = sys.stdin.readline

length = int(r())
board = list(map(int, r().split()))
answer = [0] * length
stack = []

for i in range(length - 1, -1, -1):
    while stack and stack[-1] <= board[i]:
        stack.pop()
    if not stack:
        answer[i] = -1
    else:
        answer[i] = stack[-1]
    stack.append(board[i])
for item in answer:
    print(item, end=" ")
