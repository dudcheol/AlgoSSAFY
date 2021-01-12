import sys
from collections import deque

r = sys.stdin.readline
length, target = map(int, r().split())
number = list(r().rstrip())
board = deque(list(map(int, number)))

stack = []
left = target
while board:
    cur = board.popleft()
    while stack and stack[-1] < cur and left > 0:
        left -= 1
        stack.pop()
    stack.append(cur)
while left > 0:
    stack.pop()
    left -= 1
print("".join(list(map(str, stack))))
