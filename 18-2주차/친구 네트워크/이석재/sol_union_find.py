import sys

r = sys.stdin.readline


def find(target):
    if target not in parents:
        return target
    if parents[target] != target:
        parents[target] = find(parents[target])
    return parents[target]


def union(p1, p2):
    p1_head = find(p1)
    p2_head = find(p2)
    if p1_head == p2_head:
        return max(count[p1_head], count[p2_head])

    if p1_head not in count:
        count[p1_head] = 1
    if p2_head not in count:
        count[p2_head] = 1

    parents[p2_head] = p1_head
    count[p1_head] = count[p2_head] = count[p1_head] + count[p2_head]
    return count[p1_head]


test_case_num = int(r())
for _ in range(test_case_num):
    length = int(r())
    parents = {}
    count = {}
    for i in range(length):
        p1, p2 = r().split()
        print(union(p1, p2))
