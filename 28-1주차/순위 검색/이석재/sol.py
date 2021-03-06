# 처음에는 집합의 교집합을 이용해서 해결하려고 했음, 시간초과
# 집합으로 처리하거나 이렇게 하거나 비슷한 시간이 걸릴 것이라고 생각했었음...

from bisect import bisect_left
from collections import defaultdict

def solution(infos, queries):
    dict1 = defaultdict(list)
    languages = ["cpp", "java", "python", "-"]
    positions = ["backend", "frontend", "-"]
    careers = ["senior", "junior", "-"]
    foods = ["chicken", "pizza", "-"]

    for l in languages:
        for p in positions:
            for c in careers:
                for f in foods:
                    dict1[l + p + c + f] = []

    for index, info in enumerate(infos):
        language, position, career, food, score = info.split()
        languagess = [language, "-"]
        positionss = [position, "-"]
        careerss = [career, "-"]
        foodss = [food, "-"]
        for l in languagess:
            for p in positionss:
                for c in careerss:
                    for f in foodss:
                        dict1[l + p + c + f].append(int(score))

    for item in dict1:
        dict1[item].sort()
    results = []
    for query in queries:
        language, position, career, food_score = query.split(" and ")
        food, score = food_score.split()
        candi_list = dict1[language + position + career + food]
        target_index = bisect_left(candi_list, int(score))
        # 시간초과
        # results.append(len(candi_list[target_index:]))
        # 통과
        results.append(len(candi_list) - target_index)
    return results
