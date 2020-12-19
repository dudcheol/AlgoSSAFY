import sys
from collections import deque
from itertools import combinations

r = sys.stdin.readline

input_string = r().rstrip()
list1, list2 = [], []
input_list = list(input_string)
for index, value in enumerate(input_list):
    if value == "(":
        list1.append(index)
    elif value == ")":
        list2.append(index)

list1, list2 = deque(list1), deque(list2)
# print(list1,list2)
brackets = []
while list1:
    # print(list1, list2)
    cur = list1.pop()
    temp = []
    while cur > list2[0]:
        temp.append(list2.popleft())
    brackets.append((cur, list2[0]))
    list2.popleft()
    list2 = deque(temp + list(list2))
# print(brackets)
result = []
for i in range(1, len(brackets) + 1):
    for item in combinations(brackets, i):
        # print(item)
        temp = []
        for item2 in item:
            temp.append(item2[0])
            temp.append(item2[1])
        temp.sort(reverse=True)
        temp2 = []
        for index in range(len(input_list)):
            if temp and temp[-1] == index:
                temp.pop()
                continue
            else:
                temp2.append(input_list[index])
        result.append("".join(temp2))
for item in sorted(list(set(result))):
    print(item)
