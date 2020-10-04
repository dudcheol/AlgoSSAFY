import sys
from copy import deepcopy

# 재귀 depth를 늘려줌
sys.setrecursionlimit(10 ** 8)

# 입력
r = sys.stdin.readline
board_temp = []
for i in range(4):
    board_temp.append(list(map(int, r().split())))

# 입력받은 상어를 4x4 2차원 배열에 (상어번호, 방향) 의 값을 가지도록 넣어줌
board = []
# 현재 물고기의 위치를 저장하는 자료 구조
fish_dir_dict = {}
for i in range(4):
    list1 = []
    for j in range(0, 8, 2):
        list1.append([board_temp[i][j], board_temp[i][j + 1]])
    board.append(list1)
for i in range(4):
    for j in range(4):
        fish_dir_dict[board[i][j][0]] = [i, j, board[i][j][1]]

# 0,0 위치의 물고기를 먹고, 상어의 위치와 방향을 업데이트해줌
# 먹힌 물고기 번호의 좌표 값을 [-1, -1, -1] 로 바꿔줌
shark_x = shark_y = 0
shark_size = board[0][0][0]
shark_dir = board[0][0][1]
fish_dir_dict[board[0][0][0]] = [-1, -1, -1]
board[0][0] = [-1, shark_dir]

# print(fish_dir_dict)

# 2  1  8
# 3     7
# 4  5  6

# 방향을 key로, index 변화량을 value로 하는 자료 구조 선언
dir_dict = {1: [-1, 0], 2: [-1, -1], 3: [0, -1], 4: [1, -1], 5: [1, 0], 6: [1, 1], 7: [0, 1], 8: [-1, 1]}

# 물고기를 이동시키는 함수
def move_fish():
    for i in range(1, 17):
        # 만일 물고기가 이미 잡아먹혔다면 지나감
        if fish_dir_dict[i] == [-1, -1, -1]:
            continue
        else:
            cur_fish = fish_dir_dict[i]
            cur_fish_x, cur_fish_y, cur_fish_dir = cur_fish
            # 물고기는 현재 위치를 기준으로 시계방향으로 8방향을 돌면서
            # 이동할 수 있는 최초의 칸으로 이동한다
            # 그러므로 이동하는 방향에 대한 순서를 정해줘야함
            # 배열을 선언하고 0번 인덱스에 현재 방향을 넣어주고
            # 8이 될 때 까지 하나씩 증가해서 넣어주고, 8이 되면 1로 바꾼뒤 배열 길이가 8이 될 때 까지 채움
            # 예시
            # 현재 방향이 5 -> 순서 배열 = [5,6,7,8,1,2,3,4]
            cur_fish_sequence = []
            temp_value = cur_fish_dir
            while temp_value < 9:
                cur_fish_sequence.append(temp_value)
                temp_value += 1
            temp_value = 1
            while temp_value < cur_fish_dir:
                cur_fish_sequence.append(temp_value)
                temp_value += 1
            # 방향 순서 배열에서 이동 조건에 만족하는 최초의 칸을 발견하면 이동하고 탈출
            # 발견하지 못했다면 아무것도 하지 않음
            for pos in cur_fish_sequence:
                new_x, new_y = cur_fish_x + dir_dict[pos][0], cur_fish_y + dir_dict[pos][1]

                # 배열의 index를 넘지 않고
                if 0 <= new_x < 4 and 0 <= new_y < 4:
                    # 현재 방향에 상어가 있다면 패스
                    if board[new_x][new_y][0] == -1:
                        continue
                    else:
                        # 현재 방향이 빈 칸이라면
                        # 물고기를 이동시키고
                        # 물고기 위치를 저장하고 있던 자료구조도 업데이트함
                        if board[new_x][new_y] == [0, 0]:
                            board[cur_fish_x][cur_fish_y], board[new_x][new_y] = board[new_x][new_y], board[cur_fish_x][
                                cur_fish_y]
                            board[new_x][new_y][1] = pos
                            fish_dir_dict[i] = [new_x, new_y, pos]
                            break
                        # 현재 방향에 물고기가 있다면
                        # 물고기 위치를 바꿔주고
                        # 물고기 위치를 저장하고 있던 자료구조의 값도 서로 바꿔줌
                        elif board[new_x][new_y][0] != 0:
                            moved_fish, moved_fish_dir = board[new_x][new_y]
                            board[cur_fish_x][cur_fish_y], board[new_x][new_y] = board[new_x][new_y], board[cur_fish_x][
                                cur_fish_y]
                            board[new_x][new_y][1] = pos
                            fish_dir_dict[i] = [new_x, new_y, pos]
                            fish_dir_dict[moved_fish] = [cur_fish_x, cur_fish_y, moved_fish_dir]
                            break


result = 0
# print(fish_dir_dict)

# 상어가 움직이는 함수
def move_shark(cur_x, cur_y, cur_dir, cur_size, eaten_list):
    global result, board, fish_dir_dict

    # 상어가 움직이기 전에 물고기가 움직임
    move_fish()

    # 만일 현재 몸무게가 결과 몸무게보다 크다면 업데이트 해줌
    if cur_size > result:
        result = cur_size

    # 상어는 현재 상어의 방향 경로에 있는 물고기를 잡아먹을 수 있음
    # 잡아먹고 다음 재귀를 뛰어줌
    for i in range(1, 4):
        new_x, new_y = cur_x + dir_dict[cur_dir][0] * i, cur_y + dir_dict[cur_dir][1] * i
        # 재귀를 뛰기 전에 현재 2차원 배열과 물고기 위치를 저장한 자료구조를 deepcopy해둠
        # 재귀를 넘을 때 위 자료구조를 같이 보내는 것이 아니라 전역 변수 하나로 사용하기 때문에 deepcopy를 해둬야함
        # 만일 재귀를 뛸 때 이 자료구조를 가지고 재귀를 뛰면 이렇게 안 해도 됨
        board_ori = deepcopy(board)
        fish_ori = deepcopy(fish_dir_dict)
        if 0 <= new_x < 4 and 0 <= new_y < 4:
            added_size, new_dir = board[new_x][new_y]
            # 현재 위치에 물고기가 있다면
            if board[new_x][new_y][0] != 0:
                # 먹은 물고기 리스트에 물고기 번호를 추가해줌
                eaten_list.append((new_x, new_y, added_size, new_dir))
                # 물고기 위치를 저장한 배열에 방금 먹은 물고기의 위치를 [-1,-1,-1] 로 바꿔줌
                fish_dir_dict[added_size] = [-1, -1, -1]
                # 현재 상어의 위치는 빈 공간이 되고
                board[cur_x][cur_y] = [0, 0]
                # 물고기의 위치에는 상어가 물고기의 방향을 가진 상태로 있게 됨
                board[new_x][new_y] = [-1, new_dir]
                # 이 상태에서 재귀를 뜀
                move_shark(new_x, new_y, new_dir, cur_size + added_size, eaten_list)
                # 재귀를 뛰고 돌아오면 먹은 물고기를 뱉어냄
                eaten_list.pop()
                # 재귀 전 저장했던 2차원 배열과 물고기 위치 저장 자료구조를 복사해서 재할당함
                board = deepcopy(board_ori)
                fish_dir_dict = deepcopy(fish_ori)

# 메인
move_shark(shark_x, shark_y, shark_dir, shark_size, [(0, 0, shark_size, shark_dir)])
print(result)
