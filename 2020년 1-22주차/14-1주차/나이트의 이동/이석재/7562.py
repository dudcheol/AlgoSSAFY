import sys
from collections import deque

r = sys.stdin.readline

test_case_num = int(r())
for t in range(test_case_num):
    length = int(r())
    xpo, ypo = map(int, r().split())
    desx, desy = map(int, r().split())
    queue = deque([(xpo, ypo, 0)])
    visited = [[0] * length for _ in range(length)]
    visited[xpo][ypo] = 1
    result = 0
    x = [1, 1, -1, -1, 2, 2, -2, -2]
    y = [2, -2, 2, -2, 1, -1, 1, -1]
    while queue:
        cur_x, cur_y, cur_length = queue.popleft()
        if (cur_x, cur_y) == (desx, desy):
            result = cur_length
            break
        for i in range(8):
            new_x, new_y = cur_x + x[i], cur_y + y[i]
            if 0 <= new_x < length and 0 <= new_y < length:
                if visited[new_x][new_y] == 0:
                    visited[new_x][new_y] = 1
                    queue.append((new_x, new_y, cur_length + 1))
    print(result)
