import sys
from heapq import *

r = sys.stdin.readline

picture_num = int(r())
command = int(r())
commands = list(map(int, r().split()))
h = []
s = set()
heapify(h)

for index, command in enumerate(commands):
    # print(h)
    if len(h) < picture_num:
        heappush(h, (1, index, command))
        s.add(command)
    else:
        if command in s:
            temp = []
            for item in h:
                if item[2] == command:
                    temp.append((item[0] + 1, index, command))
                else:
                    temp.append(item)
            heapify(temp)
            h = temp
        else:
            removed_vote, removed_index, removed_command = heappop(h)
            # print(removed_command)
            s.remove(removed_command)
            heappush(h, (1, index, command))
            s.add(command)

for item in sorted(list(s)):
    print(item, end=" ")
