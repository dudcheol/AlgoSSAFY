# 소용돌이를 돌리는 규칙은 찾을 수 있음
# 그대로 돌리면 되지만, 그 소용돌이를 담을 수 있는 배열사이즈는 메모리 초과가 남
# 그래서 문제에서 주어진 조건대로 결과를 담을 배열을 따로 만들고
# 소용돌이를 시뮬레이션 하면서 현재 좌표값이 내가 구하려는 범위에 유효한 경우,
# 결과를 담을 배열에 좌표값을 조정해서 담아줌
# 출력형식을 모두 동일하게 해야하기 때문에 시뮬레이션을 하면서
# 결과 배열에 담긴 값 중 가장 큰 값을 기억하고, 모든 결과숫자를 출력할때 문자열로 변환
# 길이가 부족한 경우 앞에 공백을 padding 붙여서 처리함
import sys

r = sys.stdin.readline
r1, c1, r2, c2 = map(int, r().split())

length2 = 5000

board2 = [[0] * 5 for _ in range(50)]

cur_x2, cur_y2 = 5000, 5000

target = 1
if r1 <= cur_x2 - 5000 <= r2 and c1 <= cur_y2 - 5000 <= c2:
    board2[cur_x2 - 5000 - r1][cur_y2 - 5000 - c1] = target
target += 1
gap = 1
x = [0, -1, 0, 1]
y = [1, 0, -1, 0]

max_result = 0
for _ in range(length2):
    for i in range(4):
        temp = gap
        while temp > 0:
            cur_x2, cur_y2 = cur_x2 + x[i], cur_y2 + y[i]
            if r1 <= cur_x2 - 5000 <= r2 and c1 <= cur_y2 - 5000 <= c2:
                board2[cur_x2 - 5000 - r1][cur_y2 - 5000 - c1] = target
                max_result = max(max_result, target)
            target += 1
            temp -= 1
        if i == 1:
            gap += 1
        if i == 3:
            gap += 1

for i in range(10000):
    cur_x2, cur_y2 = cur_x2 + x[0], cur_y2 + y[0]
    if r1 <= cur_x2 - 5000 <= r2 and c1 <= cur_y2 - 5000 <= c2:
        board2[cur_x2 - 5000 - r1][cur_y2 - 5000 - c1] = target
        max_result = max(max_result, target)
    target += 1


tx, ty = r2 - r1, c2 - c1
max_length = len(str(max_result))
for i in range(0, tx + 1):
    for item in board2[i][0:ty + 1]:
        print(" " * (max_length - len(str(item))) + str(item), end=" ")
    print()
