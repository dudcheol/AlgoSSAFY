import sys
from collections import deque
from copy import deepcopy

r = sys.stdin.readline

length = int(r())
board = []
for i in range(length):
    board.append(list(map(int, r().split())))

# dfs로 풀이함
# 총 가짓수는 4*4*4*4*4
# 위 아래 왼쪽 오른쪽

# 왼쪽 0 오른쪽 1 위 2 아래 3
# 2차원 배열 board를 방향 direction으로 이동했을 때의 board를 반환하는 함수

# 구현 방법
# deque 하나를 준비함
# [2,0,2,4] 라는 배열을 예시로 주석 진행
def move(board, direction):
    # 만약에 [2,0,2,4]를 왼쪽으로 밀어야한다면
    if direction == 0:
        # 최종 결과 2차원 배열을 반환할 공간을 선언
        new_board = []
        # 현재 배열에서 내부 배열을 순회하면서 처리하는데...
        for item in board:
            # 가장 마지막에 처리한 블록이 합쳐진 블록인지 아닌지 판단하는 플래그
            flag = 0
            # 빈 dequu를 하나 준비함 // [  ]   [2,0,2,4]
            temp = deque([])
            # 배열의 원소를 순회하면서 // [  ]   [2,0,2,4]
            for item2 in item:
                # 만약에 원소가 0 이면 deque에 넣지 않고 지나감 // [  ]   [0,2,4] -> [ ]    [2,4]
                if item2 == 0:
                    continue
                # 만약에 deque가 비어있다면 그냥 넣어줌 // [2  ]   [0,2,4]
                if len(temp) == 0:
                    temp.append(item2)
                # 만약에 deque가 비어있지 않다면
                else:
                    # 현재 deque의 가장 마지막 원소가 합쳐지지 않았고, 배열의 원소의 값이 같다면 // [2  ]   [2,4]
                    # deque의 마지막 원소의 값을 2배로 올려줌 // [4 ]  [4 ]
                    if flag == 0 and temp[-1] == item2:
                        temp[-1] *= 2
                        # 그리고 마지막 블록이 더해졌음을 flag 값으로 체크해줌
                        flag = 1
                    # 값이 다르거나 마지막 원소가 합쳐졌었다면 그냥 넣어줌 // [2 ]    [ 4, 8]   ==>   [2,4]    [8]
                    else:
                        temp.append(item2)
                        # 플래그는 0으로 초기화해줌
                        flag = 0
            # 나머지 길이 차이 만큼 0을 넣어줌
            for i in range(len(item) - len(temp)):
                temp.append(0)
            # [2 , 0, 4 8] --> [2,4,8,0]
            # 결과적으로 만들어진 list를 2차원 배열에 넣어줌
            new_board.append(temp)
    # 만약에 오른쪽으로 기울이는것은 정반대로 생각할 것
    elif direction == 1:
        new_board = []
        for item in board:
            flag = 0
            temp = deque([])
            item = list(item)
            for item2 in item[::-1]:
                if item2 == 0:
                    continue
                if len(temp) == 0:
                    temp.appendleft(item2)
                else:
                    if flag == 0 and temp[0] == item2:
                        temp[0] *= 2
                        flag = 1
                    else:
                        temp.appendleft(item2)
                        flag = 0
            for i in range(len(item) - len(temp)):
                temp.appendleft(0)
            new_board.append(temp)
    # 위로 기울일 때는
    elif direction == 2:
        board = list(zip(*board))
        new_board = []
        for item in board:
            flag = 0
            temp = deque([])
            for item2 in item:
                if item2 == 0:
                    continue
                if len(temp) == 0:
                    temp.append(item2)
                else:
                    if flag == 0 and temp[-1] == item2:
                        temp[-1] *= 2
                        flag = 1
                    else:
                        temp.append(item2)
                        flag = 0
            for i in range(len(item) - len(temp)):
                temp.append(0)
            new_board.append(temp)
    elif direction == 3:
        board = list(zip(*board))
        new_board = []
        for item in board:
            flag = 0
            temp = deque([])
            item = list(item)
            for item2 in item[::-1]:
                if item2 == 0:
                    continue
                if len(temp) == 0:
                    temp.appendleft(item2)
                else:
                    if flag == 0 and temp[0] == item2:
                        temp[0] *= 2
                        flag = 1
                    else:
                        temp.appendleft(item2)
                        flag = 0
            for i in range(len(item) - len(temp)):
                temp.appendleft(0)
            new_board.append(temp)
    if direction in [0, 1]:
        return new_board
    else:
        return list(zip(*new_board))


result = 0


def sol(board, count, dir_list):
    global result
    # flag = 0
    if count == 5:
        for item in board:
            for item2 in item:
                if item2 > result:
                    result = item2
                    # flag = 1
        # if flag==1:
        #     for item in board:
        #         print(item)
        #     print(dir_list)
        return
    else:
        for i in range(4):
            temp = deepcopy(dir_list)
            temp.append(i)
            sol(move(board, i), count + 1, temp)


sol(board, 0, [])
print(result)
