import sys
from collections import deque

r = sys.stdin.readline
board = []


def mapping(value):
    if value == ".":
        return 0
    elif value == "R":
        return 1
    elif value == "G":
        return 2
    elif value == "B":
        return 3
    elif value == "P":
        return 4
    elif value == "Y":
        return 5


for _ in range(12):
    board.append(list(map(lambda x: mapping(x), list(r().rstrip()))))
board = list(map(list, zip(*board)))

def bfs(xpo, ypo):
    global board
    queue = deque([])
    queue.append((xpo, ypo))

    target = board[xpo][ypo]
    board[xpo][ypo] = -1

    temp_list = [(xpo, ypo)]
    temp = 1
    x = [1, -1, 0, 0]
    y = [0, 0, 1, -1]
    while queue:
        cur_x, cur_y = queue.popleft()
        for i in range(4):
            new_x, new_y = cur_x + x[i], cur_y + y[i]
            if 0 <= new_x < 6 and 0 <= new_y < 12:
                if board[new_x][new_y] == target:
                    board[new_x][new_y] = -1
                    queue.append((new_x, new_y))
                    temp_list.append((new_x, new_y))
                    temp += 1
    if temp >= 4:
        return True
    else:
        for item in temp_list:
            board[item[0]][item[1]] = target
        return False


def refresh():
    for index, list1 in enumerate(board):
        stack1 = []
        padding = 0
        for item in list1[::-1]:
            if item != -1:
                stack1.append(item)
            else:
                padding += 1
        list1 = [0] * padding + stack1[::-1]
        board[index] = list1
    pass


#main
result = 0
while True:
    flag = 0
    for i in range(6):
        for j in range(12):
            if board[i][j] > 0:
                if bfs(i, j) is True:
                    flag = 1
    if flag == 0:
        break
    else:
        result+=1
        refresh()
print(result)
