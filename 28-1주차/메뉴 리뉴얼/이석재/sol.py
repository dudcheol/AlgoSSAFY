from itertools import combinations


def solution(orders, course):
    dict1, dict2 = {}, {}
    for item in course:
        dict2[item] = []
    for order in orders:
        order_list = sorted(list(order))
        for course_length in course:
            temp = list(combinations(order_list, course_length))
            for item in temp:
                key = "".join(item)
                if key not in dict1:
                    dict1[key] = 1
                else:
                    dict1[key] += 1
                    if not dict2[course_length] or dict2[course_length][0][1] < dict1[key]:
                        dict2[course_length] = [(key, dict1[key])]
                    elif dict2[course_length][0][1] == dict1[key]:
                        dict2[course_length].append((key, dict1[key]))
    result = []
    for key in dict2:
        for item in dict2[key]:
            result.append(item[0])
    # print(sorted(result))
    return sorted(result)
