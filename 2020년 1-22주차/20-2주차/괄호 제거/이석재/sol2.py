import sys
from itertools import combinations

r = sys.stdin.readline

input_string = r().rstrip()
list1, list2 = [], []
input_list = list(input_string)

# 유정이 코드 보고 한번에 처리해줌
stack = []
brackets = []
for index, value in enumerate(input_list):
    if value == "(":
        stack.append(index)
    elif value == ")":
        brackets.append((stack.pop(), index))

result = []
for i in range(1, len(brackets) + 1):
    for comb_list in combinations(brackets, i):
        temp = []
        for combi in comb_list:
            temp.append(combi[0])
            temp.append(combi[1])
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
