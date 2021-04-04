from itertools import product
 
 
def search():
    for i in range(N - 1, 1, -1):
        for j in range(1, i):
            for x, y in product(range(N - i), repeat=2):
                result = dessert(x, y, i - j, j)
                if result != -1: return result
    return -1
 
 
def dessert(x, y, n, m):
    y = y + n
    route = [arr[x][y]]
    for i in range(m):
        x, y = x + 1, y + 1
        if arr[x][y] in route:
            return -1
        route.append(arr[x][y])
    for i in range(n):
        x, y = x + 1, y - 1
        if arr[x][y] in route:
            return -1
        route.append(arr[x][y])
    for i in range(m):
        x, y = x - 1, y - 1
        if arr[x][y] in route:
            return -1
        route.append(arr[x][y])
    for i in range(n - 1):
        x, y = x - 1, y + 1
        if arr[x][y] in route:
            return -1
        route.append(arr[x][y])
    return len(route)
 
 
T = int(input())
for t in range(1, T + 1):
    N = int(input())
    arr = [list(map(int, input().split())) for _ in range(N)]
    print(f'#{t} {search()}')
