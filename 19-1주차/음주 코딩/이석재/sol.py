import sys

r = sys.stdin.readline


def convert(input_number):
    if input_number > 0:
        return 1
    elif input_number == 0:
        return 0
    else:
        return -1


def init(board, tree, node, start, end):
    if start == end:
        tree[node] = board[start]
        return tree[node]
    mid = (start + end) // 2
    tree[node] = init(board, tree, node * 2, start, mid) * init(board, tree, node * 2 + 1, mid + 1, end)
    return tree[node]


def update(tree, node, start, end, index, diff1, diff2):
    if not (start <= index <= end):
        return
    if start == end:
        tree[node] = diff2
    elif start != end:
        mid = (start + end) // 2
        update(tree, node * 2, start, mid, index, diff1, diff2)
        update(tree, node * 2 + 1, mid + 1, end, index, diff1, diff2)
        tree[node] = tree[node * 2] * tree[node * 2 + 1]


def mul(tree, node, start, end, left, right):
    if right < start or left > end:
        return 1
    if left <= start and end <= right:
        return tree[node]
    else:
        mid = (start + end) // 2
        return mul(tree, node * 2, start, mid, left, right) * mul(tree, node * 2 + 1, mid + 1, end, left, right)


while True:
    line = r().rstrip()
    if len(line) < 1:
        break
    board_length, command_length = map(int, line.split())
    board = list(map(int, r().split()))
    board = list(map(lambda x: convert(x), board))
    tree_length = board_length * 4
    # 이렇게 하면 항상 만족함 아래는 메모리를 최대한 맞게 잡는경우
    # tree_length = pow(2, math.ceil(math.log2(board_length)) + 1)
    tree = [0 for _ in range(tree_length)]
    init(board, tree, 1, 0, board_length - 1)
    # print(tree)
    results = []
    for i in range(command_length):
        command = r().rstrip().split()
        flag, t1, t2 = command[0], int(command[1]), int(command[2])
        # print(flag, t1, t2)
        if flag == "C":
            update(tree, 1, 0, board_length - 1, t1 - 1, board[t1 - 1], convert(t2))
            board[t1 - 1] = convert(t2)
        elif flag == "P":
            result = mul(tree, 1, 0, board_length - 1, t1 - 1, t2 - 1)
            if result > 0:
                results.append("+")
            elif result == 0:
                results.append("0")
            else:
                results.append("-")
        # print(tree)
    print("".join(results))
