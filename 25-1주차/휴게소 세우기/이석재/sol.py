import sys

r = sys.stdin.readline
point_num, added_num, length = map(int, r().split())
board = [0] + list(map(int, r().split())) + [length]
board.sort()
# print(board)
low, high = 0, length
result = float("inf")
while low <= high:
    mid = (low + high) // 2
    temp = 0
    for i in range(1, len(board)):
        temp += (board[i] - board[i - 1] - 1) // mid
    # print(low, high, mid, temp)
    if temp > added_num:
        low = mid + 1
    else:
        result = min(result, mid)
        high = mid - 1
print(result)
