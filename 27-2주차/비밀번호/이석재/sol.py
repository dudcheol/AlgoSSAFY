import sys

r = sys.stdin.readline
length, known_length = map(int, r().split())
known = r().split()
target = length - known_length
count = 0


def sol(cur):
    global count
    if len(cur) == length:
        set1 = set(list(cur))
        for item in known:
            if item not in set1:
                break
        else:
            count += 1
        return
    for c in list(map(str, [i for i in range(10)])):
        sol(cur + c)


sol("")
print(count)
