import sys

r = sys.stdin.readline


def solve(left, right):
    global board

    if left == right:
        return board[left]

    mid = (left + right) // 2
    result = max(solve(left, mid), solve(mid + 1, right))

    low, high = mid, mid + 1
    height = min(board[low], board[high])
    result = max(result, height * 2)

    while left < low or high < right:
        if high < right and (low <= left or board[low - 1] < board[high + 1]):
            high += 1
            height = min(board[high], height)
        else:
            low -= 1
            height = min(board[low], height)
        result = max(result, height * (high - low + 1))
    return result


length = int(r())
board = list(map(int, r().split()))
print(solve(0, length - 1))
