# https://rhdtka21.tistory.com/105

import sys

r = sys.stdin.readline

board = []
result = float("inf")
for i in range(10):
    list1 = list(map(int, r().split()))
    board.append(list1)

paper_dict = {}
for i in range(1, 6):
    paper_dict[i] = 5


def print_board(board):
    for i in range(10):
        print(board[i])


def is_possible(r, c, size):
    for i in range(r, r + size):
        for j in range(c, c + size):
            if not (0 <= i < 10 and 0 <= j < 10):
                return False
            if board[i][j] == 0:
                return False
    return True


def do(row, col, size, method):
    for i in range(row, row + size):
        for j in range(col, col + size):
            board[i][j] = method


def sol(index, used_paper_num):
    global result, paper_dict
    if used_paper_num >= result:
        return
    if index == 100:
        result = min(result, used_paper_num)
    else:
        row, col = index // 10, index % 10
        max_size = 0

        for size in range(5, 0, -1):
            if is_possible(row, col, size):
                max_size = size
                break
        if board[row][col] == 1:
            for size in range(max_size, 0, -1):
                if paper_dict[size] > 0:
                    do(row, col, size, 0)
                    paper_dict[size] -= 1
                    sol(index + 1, used_paper_num + 1)
                    paper_dict[size] += 1
                    do(row, col, size, 1)
        else:
            sol(index + 1, used_paper_num)


sol(0, 0)
print(result if result != float("inf") else -1)
