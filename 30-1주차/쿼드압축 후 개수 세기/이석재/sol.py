dict1 = {1: 0, 0: 0}


def sol(x, y, length, arr):
    global dict1
    flag = 0
    for i in range(length):
        for j in range(length):
            if arr[x][y] != arr[x + i][y + j]:
                flag = 1
                break
        if flag:
            break
    if not flag:
        dict1[arr[x][y]] += 1
        # print(f"{x} {y} {length} 에 {arr[x][y]} 가 {1} 개 더해짐")
    else:
        sol(x, y, length // 2, arr)
        sol(x + length // 2, y, length // 2, arr)
        sol(x, y + length // 2, length // 2, arr)
        sol(x + length // 2, y + length // 2, length // 2, arr)


def solution(arr):
    global dict1

    sol(0, 0, len(arr), arr)
    return [dict1[0], dict1[1]]


arr = [[1, 1, 0, 0],
       [1, 0, 0, 0],
       [1, 0, 0, 1],
       [1, 1, 1, 1]]
print(solution(arr))
arr2 = [[1, 1, 1, 1, 1, 1, 1, 1],
        [0, 1, 1, 1, 1, 1, 1, 1],
        [0, 0, 0, 0, 1, 1, 1, 1],
        [0, 1, 0, 0, 1, 1, 1, 1],
        [0, 0, 0, 0, 0, 0, 1, 1],
        [0, 0, 0, 0, 0, 0, 0, 1],
        [0, 0, 0, 0, 1, 0, 0, 1],
        [0, 0, 0, 0, 1, 1, 1, 1]]
print(solution(arr2))
