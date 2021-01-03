import sys
from collections import Counter

r = sys.stdin.readline

height, width = map(int, r().split())
board = []

for i in range(height):
    list1 = list(r().rstrip())
    board.append(list1)
commands = list(map(int, list(r().rstrip())))
curx, cury = 0, 0
mad = []
for i in range(height):
    for j in range(width):
        if board[i][j] == "I":
            curx, cury = i, j
            board[i][j] = "."
        elif board[i][j] == "R":
            mad.append((i, j))
            board[i][j] = "."

x = [-1, 1, 1, 1, 0, 0, 0, -1, -1, -1]
y = [-1, -1, 0, 1, -1, 0, 1, -1, 0, 1]


def cal_dist(x1, y1, x2, y2):
    return abs(x1 - x2) + abs(y1 - y2)


# 인범이의 움직임은 좌표평면을 넘지 않지만
# 아두이노는 그렇지 않기 때문에 핸들해주어야함
# PYTHON의 경우 음값의 인덱스가 정상적으로 작동함으로 작성 시 주의할 것
def move():
    global mad
    temp = []
    for i in range(len(mad)):
        axpo, aypo = mad[i]
        tx, ty = 0, 0
        cur_dist = float("inf")
        for dirr in range(1, 10):
            if 0 <= axpo + x[dirr] < height and 0 <= aypo + y[dirr] < width:
                if cal_dist(curx, cury, axpo + x[dirr], aypo + y[dirr]) < cur_dist:
                    cur_dist = cal_dist(curx, cury, axpo + x[dirr], aypo + y[dirr])
                    tx, ty = axpo + x[dirr], aypo + y[dirr]
        # 아두이노는 인범이와 맨해튼 거리 기준 가장 가까운 하나의 좌표로 이동함
        # 인범이가 그 위치에 있으면 탈출
        if tx == curx and ty == cury:
            return False
        temp.append((tx, ty))
    t = Counter(temp)
    temp2 = []
    # Counter를 사용해서 유일한 값인 좌표만 결과배열에 넣어줌
    # 1을 초과하는 값이라면 아두이도가 충돌해서 터지는 경우임
    for item in t:
        if t[item] == 1:
            temp2.append(item)
    mad = temp2

# 결국 좌표값만 가지고 조작할 수 있음
# 인범이를 움직이고, 인범이 위치에 아두이노가 있으면 탈출
# 아두이노를 움직이고 아두이노 위치가 인범이 위치에 있으면 탈출
# 그게 아니면 시뮬레이션 후 최종 위치 값에 R, I 를 적어서 출력

time = 0
for command in commands:
    time += 1
    curx, cury = curx + x[command], cury + y[command]
    if (curx, cury) in mad:
        print(f"kraj {time}")
        break
    else:
        if move() is False:
            print(f"kraj {time}")
            break
else:
    board[curx][cury] = "I"
    for item in mad:
        board[item[0]][item[1]] = "R"
    for item in board:
        print("".join(item))
