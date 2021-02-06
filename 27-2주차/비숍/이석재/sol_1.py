import sys

r = sys.stdin.readline
sys.setrecursionlimit(10 ** 5)
length = int(r())
board = []
for i in range(length):
    list1 = list(map(int, r().split()))
    board.append(list1)

# x+y
diagonal1 = [0] * (length * 2 - 1)
# length-1 + (x-y)
diagonal2 = [0] * (length * 2 - 1)

count1 = count2 = count = 0


def sol(level, flag):
    global count, count1, count2
    if level >= length * 2:
        return
    cur_x = level if level < length else length - 1
    cur_y = level - cur_x
    while cur_y < length and cur_x > -1:
        if board[cur_x][cur_y] == 1:
            if diagonal2[length - 1 + (cur_x - cur_y)] == 0:
                count += 1
                if flag == 1:
                    count1 = max(count1, count)
                else:
                    count2 = max(count2, count)
                diagonal2[length - 1 + (cur_x - cur_y)] = 1
                sol(level + 2, flag)
                diagonal2[length - 1 + (cur_x - cur_y)] = 0
                count -= 1
        cur_x -= 1
        cur_y += 1
    sol(level + 2, flag)


sol(0, 1)
count = 0
sol(1, 2)
print(count1 + count2)
