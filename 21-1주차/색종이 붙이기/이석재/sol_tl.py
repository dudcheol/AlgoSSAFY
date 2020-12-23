import sys
from copy import *

r = sys.stdin.readline

board = []
spots = set()
spots_list = []
result = float("inf")
for i in range(10):
    list1 = list(map(int, r().split()))
    for j in range(10):
        if list1[j] == 1:
            spots.add((i, j))
            spots_list.append((i, j))
    board.append(list1)
spots_list.sort(reverse=True)

paper_dict = {}
for i in range(1, 6):
    paper_dict[i] = 5


def print_board(board):
    for i in range(10):
        print(board[i])


def sol(used_paper_num):
    global spots, spots_list, result, paper_dict
    if used_paper_num >= result:
        return
    if len(spots_list) == 0:
        result = min(result, used_paper_num)
        return
    else:
        for i in range(5, 0, -1):
            cur = spots_list[-1]
            if paper_dict[i] > 0:
                bucket = []
                for p in range(cur[0], cur[0] + i):
                    for q in range(cur[1], cur[1] + i):
                        if (p, q) in spots_list:
                            bucket.append((p, q))
                if len(bucket) == i * i:
                    paper_dict[i] -= 1
                    spots_list = list(filter(lambda x: x not in set(bucket), spots_list))
                    sol(used_paper_num + 1)
                    paper_dict[i] += 1
                    spots_list += bucket
                    spots_list.sort(reverse=True)


sol(0)
print(result if result != float("inf") else -1)
