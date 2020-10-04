import sys
from collections import deque

# 입력 단계
r = sys.stdin.readline
height, width = map(int, r().split())
board = []


# mapping을 위한 함수 선언
# token에 따라서 해당하는 정수값으로 변환하는 함수
def map_function(target):
    if target == "#":
        return 1
    elif target == ".":
        return 0
    elif target == "O":
        return -1
    elif target == "R":
        return 2
    elif target == "B":
        return 3


# 입력 받은 문자열을 위에 선언한 mapping 함수를 적용한 결과 배열을 저장하는 2차원 배열을 선언
for i in range(height):
    list1 = list(r().rstrip())
    board.append(list(map(map_function, list1)))

# 0 빈자리, 1 벽, -1 구멍, 2 빨간공, 3 파란공
# 배열을 탐색하면서 빨간공, 파란공, 구멍의 x,y 좌표 값을 저장해둠
# 공의 경우는 2차원 배열에서 위치를 빈 곳으로 바꿔놔줌
redx = redy = bluex = bluey = holex = holey = 0
for i in range(height):
    for j in range(width):
        if board[i][j] == 2:
            redx = i
            redy = j
            board[i][j] = 0
        elif board[i][j] == 3:
            bluex = i
            bluey = j
            board[i][j] = 0
        elif board[i][j] == -1:
            holex = i
            holey = j

x = [-1, 1, 0, 0]
y = [0, 0, -1, 1]


# 빨간공, 파란공, 기울이는 방향을 입력하면
# 빨간공, 파란공의 위치를 반환하는 함수
def move(redx, redy, bluex, bluey, direction):
    sequence = []
    flag = 0
    # 만일 기울이는 방향이 위쪽이고
    if direction == 0:
        # 만약에 빨간공, 파란공이 동일선상에 있는 상황이라면
        if redy == bluey:
            # 빨간공이 파란공보다 위에 있다면
            # 빨간공을 먼저 이동시키고 파란공을 이동시켜야함
            # 그러므로 sequence 배열에 빨간공을 먼저 넣어주고 파란공을 넣어줌
            if redx < bluex:
                sequence.append((redx, redy))
                sequence.append((bluex, bluey))
            # 그 반대라면 파란공을 먼저 넣어주고 빨간공을 넣어줌
            else:
                flag = 1
                sequence.append((bluex, bluey))
                sequence.append((redx, redy))
        # 만일 동일선상에 있는게 아니라면, 순서가 상관없기때문에 빨간공 부터 처리해줌
        else:
            sequence.append((redx, redy))
            sequence.append((bluex, bluey))
    elif direction == 1:
        if redy == bluey:
            if redx > bluex:
                sequence.append((redx, redy))
                sequence.append((bluex, bluey))
            else:
                flag = 1
                sequence.append((bluex, bluey))
                sequence.append((redx, redy))
        else:
            sequence.append((redx, redy))
            sequence.append((bluex, bluey))
    elif direction == 2:
        if redx == bluex:
            if redy < bluey:
                sequence.append((redx, redy))
                sequence.append((bluex, bluey))
            else:
                flag = 1
                sequence.append((bluex, bluey))
                sequence.append((redx, redy))
        else:
            sequence.append((redx, redy))
            sequence.append((bluex, bluey))
    elif direction == 3:
        if redx == bluex:
            if redy > bluey:
                sequence.append((redx, redy))
                sequence.append((bluex, bluey))
            else:
                flag = 1
                sequence.append((bluex, bluey))
                sequence.append((redx, redy))
        else:
            sequence.append((redx, redy))
            sequence.append((bluex, bluey))
    # print(sequence)

    # 이동한 빨간공, 파란공의 위치를 담을 공간
    result_position = []
    tempx = tempy = 0
    for i in range(2):
        cur_x, cur_y = sequence[i]
        # 현재 공을 기울이는 방향으로 벽이나 구멍을 만나기 전 까지 계속 이동시킴
        while 0 <= cur_x + x[direction] < height and 0 <= cur_y + y[direction] < width and board[cur_x + x[direction]][
            cur_y + y[direction]] in [0, -1]:
            cur_x += x[direction]
            cur_y += y[direction]
            # 만약에 현재 위치가 구멍의 위치라면 다음 공을 처리하기 위해 탈출함
            if board[cur_x][cur_y] == -1:
                break
        # 만약에 첫번째 공을 다 이동시켰는데 그 위치가 빈 자리라면
        # 두번째 공이 이동할때는 첫번째 공의 위치는 벽으로 처리해줌
        if i == 0 and board[cur_x][cur_y] == 0:
            tempx = cur_x
            tempy = cur_y
            board[tempx][tempy] = 100
        # 위 조건에서 결과적으로 기울였을 때 공의 최종 위치를 배열에 담아줌
        result_position.append((cur_x, cur_y))
    # 첫 번째 공의 위치에 세웠던 벽(100)값을 없애줌
    board[tempx][tempy] = 0
    # 위에 선언한 flag 값을 이용해
    # 만일 파란공먼저 처리했으면 결과 배열 순서를 뒤집어서 보내고
    # 그렇지 않으면 정상적으로 보냄
    return result_position if flag == 0 else result_position[::-1]


# 방문 처리를 위한 집합
visited = set()
# BFS를 위한 deque
queue = deque([])
# deque에 빨간공 파란공의 시작위치의 좌표값과 이동한 시간(초깃값=0)을 넣어줌
queue.append((redx, redy, bluex, bluey, 0))
# 방문 처리 집합에는 빨간, 파란공의 좌표값을 넣어줌
visited.add((redx, redy, bluex, bluey))
result = -1
while queue:
    # 큐에서 빨간공, 파란공의 좌표값을 꺼냄
    cur_redx, cur_redy, cur_bluex, cur_bluey, cur_length = queue.popleft()
    # 빨간공의 좌표가 구멍의 좌표와 같다면 그 때 결과값을 저장하고 탐색 종료
    if cur_redx == holex and cur_redy == holey:
        result = cur_length
        break

    # 4가지 방향으로 기울여보면서
    for i in range(4):
        # 기울였을 때 빨간공의 위치값과 파란공의 위치값을 받아옴
        new_red, new_blue = move(cur_redx, cur_redy, cur_bluex, cur_bluey, i)
        new_redx, new_redy = new_red
        new_bluex, new_bluey = new_blue

        # 만약에 파란공의 위치가 구멍의 위치라면 큐에 삽입하지 않고 지나간다
        if new_bluex == holex and new_bluey == holey:
            continue

        # move 함수에서 두 공의 좌표값이 같은 경우는 존재하지 않게 처리했기 때문에 그럴일이 없겠지만
        # 혹시나 그럴일은 없지면 공의 좌표값이 같다면 continue 함
        if (new_redx, new_redy) == (new_bluex, new_bluey):
            continue

        # 여기까지 통과했을 때 현재 빨간공, 파란공의 좌표값은 유효하기 때문에 큐와 방문 좌표 집합에 넣어줌
        if (new_redx, new_redy, new_bluex, new_bluey) not in visited:
            visited.add((new_redx, new_redy, new_bluex, new_bluey))
            queue.append((new_redx, new_redy, new_bluex, new_bluey, cur_length + 1))
# 문제 조건 다시 읽을 것
# 만약에 결과값이 10보다 크면 -1을 출력해줌
print(result if result <= 10 else -1)
