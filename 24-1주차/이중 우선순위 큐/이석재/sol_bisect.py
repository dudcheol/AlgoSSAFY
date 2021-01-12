import sys
from collections import deque
from bisect import *

r = sys.stdin.readline

test_case = int(r())
for t in range(test_case):
    length = int(r())
    dq = deque([])
    dict1 = {}
    for i in range(length):
        commands = r().split()
        command, value = commands
        value = int(value)
        if command == "I":
            try:
                dict1[value] += 1
            except Exception:
                dict1[value] = 1
                insort_left(dq, value)
        elif command == "D":
            if not dq:
                continue
            if value == 1:
                if dict1[dq[-1]] == 1:
                    dict1.pop(dq.pop())
                else:
                    dict1[dq[-1]] -= 1
            else:
                if dict1[dq[0]] == 1:
                    dict1.pop(dq.popleft())
                else:
                    dict1[dq[0]] -= 1
    if not dq:
        print("EMPTY")
    else:
        print(dq[-1], dq[0])
