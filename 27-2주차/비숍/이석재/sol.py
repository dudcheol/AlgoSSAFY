import sys

r = sys.stdin.readline
sys.setrecursionlimit(10 ** 5)
length = int(r())
board = []
for i in range(length):
    list1 = list(map(int, r().split()))
    board.append(list1)

result = [0, 0]

d1 = [0] * 20
d2 = [0] * 20


def sol(row, col, count, color):
    if col >= length:
        row += 1
        if col % 2 == 0:
            col = 1
        else:
            col = 0
    if row >= length:
        result[color] = max(result[color], count)
        return
    if board[row][col] and not d1[col - row + length - 1] and not d2[row + col]:
        d1[col - row + length - 1] = d2[row + col] = 1
        sol(row, col + 2, count + 1, color)
        d1[col - row + length - 1] = d2[row + col] = 0
    sol(row, col + 2, count, color)


sol(0, 0, 0, 0)
sol(0, 1, 0, 1)
print(sum(result))
