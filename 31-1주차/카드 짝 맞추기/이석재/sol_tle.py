# python은 구현도 최적화를 하지 않으면 빡구현 문제는 tle가 남
# 수정하긴 지침ㅠㅠ;


from itertools import permutations, product
from collections import defaultdict, deque
from copy import deepcopy


def bfs(start_pos, end_pos, board):
    sx, sy = start_pos
    ex, ey = end_pos
    q = deque([(sx, sy, 0)])
    xpo, ypo = [1, -1, 0, 0], [0, 0, 1, -1]
    answer = 0
    visited = [[0] * 4 for _ in range(4)]
    visited[sx][sy] = 1
    while q:
        cur_x, cur_y, cur_length = q.popleft()
        if (cur_x, cur_y) == (ex, ey):
            answer = cur_length
            break
        # 4방으로 한칸만 가기
        for i in range(4):
            new_x, new_y = cur_x + xpo[i], cur_y + ypo[i]
            if 0 <= new_x < 4 and 0 <= new_y < 4:
                if not visited[new_x][new_y]:
                    visited[new_x][new_y] = 1
                    q.append((new_x, new_y, cur_length + 1))
        # 4방으로 쭉 가기
        temp_x, temp_y = cur_x, cur_y
        while temp_x != 0:
            temp_x -= 1
            if board[temp_x][temp_y] != 0:
                break
        if not visited[temp_x][temp_y]:
            visited[temp_x][temp_y] = 1
            q.append((temp_x, temp_y, cur_length + 1))

        temp_x, temp_y = cur_x, cur_y
        while temp_x != 3:
            temp_x += 1
            if board[temp_x][temp_y] != 0:
                break

        if not visited[temp_x][temp_y]:
            visited[temp_x][temp_y] = 1
            q.append((temp_x, temp_y, cur_length + 1))

        temp_x, temp_y = cur_x, cur_y
        while temp_y != 0:
            temp_y -= 1
            if board[temp_x][temp_y] != 0:
                break
        if not visited[temp_x][temp_y]:
            visited[temp_x][temp_y] = 1
            q.append((temp_x, temp_y, cur_length + 1))

        temp_x, temp_y = cur_x, cur_y
        while temp_y != 3:
            temp_y += 1
            if board[temp_x][temp_y] != 0:
                break
        if not visited[temp_x][temp_y]:
            visited[temp_x][temp_y] = 1
            q.append((temp_x, temp_y, cur_length + 1))
    board[end_pos[0]][end_pos[1]] = 0
    return answer + 1, board


def solution3(prod, r, c, board):
    answer = 0
    pos_list = [(r, c)]
    for pos in prod:
        pos_list += pos
    pos_list = deque(pos_list)
    while len(pos_list) > 1:
        cur = pos_list.popleft()
        length, board = bfs(cur, pos_list[0], board)
        answer += length
        # board[pos_list[0][0]][pos_list[0][1]] = 0
    return answer


def solution2(permu, r, c, board):
    global card_pos
    answer = float("inf")
    permu_list = list(permu)
    permu_list2 = []
    for card_no in permu_list:
        permu_list2.append([card_pos[card_no], card_pos[card_no][::-1]])
    for prod in product(*permu_list2):
        board2 = deepcopy(board)
        answer2 = 0
        pos_list = [(r, c)]
        for pos in prod:
            pos_list += pos
        pos_list = deque(pos_list)
        while len(pos_list) > 1:
            cur = pos_list.popleft()

            # length, board2 = bfs(cur, pos_list[0], board2)

            sx, sy = cur
            ex, ey = pos_list[0]
            end_pos = pos_list[0]
            q = deque([(sx, sy, 0)])
            xpo, ypo = [1, -1, 0, 0], [0, 0, 1, -1]
            answer3 = 0
            visited = [[0] * 4 for _ in range(4)]
            visited[sx][sy] = 1
            while q:
                cur_x, cur_y, cur_length = q.popleft()
                if (cur_x, cur_y) == (ex, ey):
                    answer3 = cur_length
                    break
                # 4방으로 한칸만 가기
                for i in range(4):
                    new_x, new_y = cur_x + xpo[i], cur_y + ypo[i]
                    if 0 <= new_x < 4 and 0 <= new_y < 4:
                        if not visited[new_x][new_y]:
                            visited[new_x][new_y] = 1
                            q.append((new_x, new_y, cur_length + 1))
                # 4방으로 쭉 가기
                temp_x, temp_y = cur_x, cur_y
                while temp_x != 0:
                    temp_x -= 1
                    if board2[temp_x][temp_y] != 0:
                        break
                if not visited[temp_x][temp_y]:
                    visited[temp_x][temp_y] = 1
                    q.append((temp_x, temp_y, cur_length + 1))

                temp_x, temp_y = cur_x, cur_y
                while temp_x != 3:
                    temp_x += 1
                    if board2[temp_x][temp_y] != 0:
                        break

                if not visited[temp_x][temp_y]:
                    visited[temp_x][temp_y] = 1
                    q.append((temp_x, temp_y, cur_length + 1))

                temp_x, temp_y = cur_x, cur_y
                while temp_y != 0:
                    temp_y -= 1
                    if board2[temp_x][temp_y] != 0:
                        break
                if not visited[temp_x][temp_y]:
                    visited[temp_x][temp_y] = 1
                    q.append((temp_x, temp_y, cur_length + 1))

                temp_x, temp_y = cur_x, cur_y
                while temp_y != 3:
                    temp_y += 1
                    if board2[temp_x][temp_y] != 0:
                        break
                if not visited[temp_x][temp_y]:
                    visited[temp_x][temp_y] = 1
                    q.append((temp_x, temp_y, cur_length + 1))
            board2[end_pos[0]][end_pos[1]] = 0
            answer2 += (answer3+1)
        answer = min(answer, answer2)
    return answer


def solution(board, r, c):
    global card_pos
    answer = float("inf")
    card_list = set()
    card_pos = defaultdict(list)
    for i in range(4):
        for j in range(4):
            if board[i][j] != 0:
                card_list.add(board[i][j])
            card_pos[board[i][j]].append((i, j))
    card_list = sorted(list(card_list))
    for permu in permutations(card_list, len(card_list)):
        answer = min(answer, solution2(permu, r, c, board))
    return answer
