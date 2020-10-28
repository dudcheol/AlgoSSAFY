import sys

r = sys.stdin.readline

height, width = map(int, r().split())
board = list(map(int, r().split()))

stack = []
result = 0
for i in range(len(board)):
    if not stack:
        stack.append(i)
    else:
        while board[stack[-1]] < board[i]:
            cur = stack.pop()
            if not stack:
                break
            width = i - stack[-1] - 1
            height = min(board[i], board[stack[-1]]) - board[cur]
            result += width * height
        stack.append(i)
print(result)
