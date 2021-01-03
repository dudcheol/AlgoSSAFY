# https://justicehui.github.io/usaco/2019/04/11/BOJ6198/
# https://velog.io/@pss407/%EB%B0%B1%EC%A4%806198-%EC%98%A5%EC%83%81-%EC%A0%95%EC%9B%90-%EA%BE%B8%EB%AF%B8%EA%B8%B0
# 
import sys
from collections import deque

r = sys.stdin.readline

length = int(r())
board = []
for i in range(length):
    board.append(int(r()))
stack = []
result = 0
for i in range(length):
    while stack:
        if stack[-1] <= board[i]:
            stack.pop()
        else:
            break
    result += len(stack)
    stack.append(board[i])
print(result)
