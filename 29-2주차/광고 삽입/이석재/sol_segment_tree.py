# segment tree with lazy propagation

def init(tree, node, start, end):
    if start == end:
        tree[node] = 0
        return tree[node]
    mid = (start + end) // 2
    tree[node] = init(tree, node * 2, start, mid) + init(tree, node * 2 + 1, mid + 1, end)
    return tree[node]


def update(tree, node, start, end, index, diff):
    if not (start <= index <= end):
        return
    tree[node] += diff
    if start != end:
        mid = (start + end) // 2
        update(tree, node * 2, start, mid, index, diff)
        update(tree, node * 2 + 1, mid + 1, end, index, diff)


# lazy 를 관리하기 때문에 특정 구간의 합을 구할 때도 lazy를 반영해주고 구해야함
def sum(tree, lazy, node, start, end, left, right):
    update_lazy(tree, lazy, node, start, end)
    if right < start or left > end:
        return 0
    if left <= start and end <= right:
        return tree[node]
    else:
        mid = (start + end) // 2
        return sum(tree, lazy, node * 2, start, mid, left, right) + sum(tree, lazy, node * 2 + 1, mid + 1, end, left,
                                                                        right)


# lazy 값이 있다면 lazy 값을 반영하고 자식 노드에게 lazy 값을 전파하는 함수
def update_lazy(tree, lazy, node, start, end):
    if lazy[node] == 0:
        return
    tree[node] += (end - start + 1) * lazy[node]
    if start != end:
        lazy[node * 2] += lazy[node]
        lazy[node * 2 + 1] += lazy[node]
    lazy[node] = 0


def update_range(tree, lazy, node, start, end, left, right, diff):
    update_lazy(tree, lazy, node, start, end)
    if right < start or left > end:
        return
    if left <= start and end <= right:
        tree[node] += (end - start + 1) * diff
        if start != end:
            lazy[node * 2] += diff
            lazy[node * 2 + 1] += diff
        return
    mid = (start + end) // 2
    update_range(tree, lazy, node * 2, start, mid, left, right, diff)
    update_range(tree, lazy, node * 2 + 1, mid + 1, end, left, right, diff)

    tree[node] = (tree[node * 2] + tree[node * 2 + 1])


def time_to_sec(time_string):
    hour, minute, second = time_string.split(":")
    hour, minute, second = int(hour), int(minute), int(second)
    return second + minute * 60 + hour * 3600


def sec_to_time(second_number):
    hour = second_number // 3600
    left = second_number % 3600
    minute = left // 60
    left = left % 60
    second = left
    hour_string = ("0" if hour < 10 else "") + str(hour)
    minute_string = ("0" if minute < 10 else "") + str(minute)
    second_string = ("0" if second < 10 else "") + str(second)

    return hour_string + ":" + minute_string + ":" + second_string


def solution(play_time, adv_time, logs):
    board_length = 360001
    tree_length = board_length * 4
    tree = [0 for _ in range(tree_length)]
    lazy = [0 for _ in range(tree_length)]

    # init(tree, 1, 0, board_length - 1)

    for log in logs:
        start_time, end_time = log.split("-")
        start_sec, end_sec = time_to_sec(start_time), time_to_sec(end_time)
        update_range(tree, lazy, 1, 0, board_length - 1, start_sec, end_sec - 1, 1)

    max_time = time_to_sec(play_time) + 1
    ad_time = time_to_sec(adv_time)
    max_val, result = 0, 0

    for i in range(1, max_time):
        end = i + ad_time
        if end > max_time:
            break
        temp = sum(tree, lazy, 1, 0, board_length - 1, i - 1, end - 2)
        if temp > max_val:
            max_val = temp
            result = i - 1

    return sec_to_time(result)

# play_time = "02:03:55"
# adv_time = "00:14:15"
# logs = ["01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"]

# play_time = "99:59:59"
# adv_time = "25:00:00"
# logs = ["69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"]

# play_time = "50:00:00"
# adv_time = "50:00:00"
# logs = ["15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"]
# print(solution(play_time, adv_time, logs))
