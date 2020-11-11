# 스택을 사용한 방법

import sys

r = sys.stdin.readline

length = int(r())
board = list(map(int, r().split()))
board = [0] + board
stack = [0]
result = 0
for index in range(1, length + 1):
    if board[stack[-1]] > board[index]:
        while board[stack[-1]] >= board[index]:
            height = board[stack[-1]]
            stack.pop()
            if not stack:
                break
            width = index - stack[-1] - 1
            result = max(result, width * height)
    stack.append(index)

if stack[-1] > 0:
    while stack[-1] > 0:
        height = board[stack[-1]]
        stack.pop()
        if not stack:
            break
        width = (length + 1) - stack[-1] - 1
        result = max(result, width * height)
print(result)
