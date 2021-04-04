from collections import deque


def is_possible(x, y, length):
    if 0 <= x < length and 0 <= y < length:
        return True
    return False


def dfs(cur_x, cur_y, cur_dir, seq, visited, tx, ty):
    global answer, board, direction
    # print(seq)
    if len(seq) == 0:
        if (cur_x, cur_y) == (tx, ty):
            answer = max(answer, len(visited))
            return

    if cur_dir == 0:
        for direc in direction:
            new_dir = direc
            new_x, new_y = cur_x + direction[direc][0], cur_y + direction[direc][1]
            if is_possible(new_x, new_y, cafe_length):
                visited.add(board[new_x][new_y])
                temp = list(seq)
                n_seq = deque(temp[temp.index(direc):temp.index(direc) + 4])
                n_seq.popleft()
                # print(f"{new_x},{new_y} 진입.")
                dfs(new_x, new_y, new_dir, n_seq, visited, tx, ty)
                visited.remove(board[new_x][new_y])
    else:
        new_x, new_y = cur_x + direction[cur_dir][0], cur_y + direction[cur_dir][1]
        if is_possible(new_x, new_y, cafe_length):
            if board[new_x][new_y] not in visited:
                visited.add(board[new_x][new_y])
                # print(f"{new_x},{new_y} 진입.")
                dfs(new_x, new_y, cur_dir, seq, visited, tx, ty)
                visited.remove(board[new_x][new_y])
        if seq:
            direc = seq.popleft()
            new_dir = direc
            new_x, new_y = cur_x + direction[direc][0], cur_y + direction[direc][1]
            if is_possible(new_x, new_y, cafe_length):
                if board[new_x][new_y] not in visited:
                    visited.add(board[new_x][new_y])
                    # print(f"{new_x},{new_y} 진입.")
                    dfs(new_x, new_y, new_dir, seq, visited, tx, ty)
                    visited.remove(board[new_x][new_y])
            seq.appendleft(direc)


test_case = int(input())

for t in range(1, test_case + 1):
    cafe_length = int(input())
    answer = -1
    board = []
    direction = {2: [-1, -1],
                 1: [-1, 1],
                 -1: [1, -1],
                 -2: [1, 1]}
    seq1 = [1, -2, -1, 2, 1, -2, -1, 2]
    # seq2 = [2, -1, -2, 1]
    for i in range(cafe_length):
        board.append(list(map(int, input().split())))
    for i in range(cafe_length):
        for j in range(cafe_length):
            q1 = deque(seq1)
            visited1 = set()
            # print(f"{i} {j} start")
            dfs(i, j, 0, q1, visited1, i, j)
    print(f"#{t} {answer}")
