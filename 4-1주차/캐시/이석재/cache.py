#https://programmers.co.kr/learn/courses/30/lessons/17680
def solution(cacheSize, cities):
    cities=list(map(lambda x:x.lower(),cities))
    cache={}
    result=0
    if cacheSize==0:
        return 5*len(cities)
    for item in cities:
        if len(cache)<cacheSize:
            if item not in cache:
                for item2 in cache:
                    cache[item2] += 1
                cache[item]=0
                result+=5
            else:
                for item2 in cache:
                    if item2 != item:
                        cache[item2] += 1
                cache[item] = 0
                result += 1
            pass
        else:
            if item not in cache:
                max1 = 0
                maxkey = ""
                for item2 in cache:
                    if cache[item2] >= max1:
                        max1 = cache[item2]
                        maxkey = item2
                if maxkey != "":
                    # print(maxkey)
                    del cache[maxkey]
                for item2 in cache:
                    cache[item2] += 1
                cache[item] = 0
                result += 5
            elif item in cache:
                for item2 in cache:
                    if item2 != item:
                        cache[item2] += 1
                cache[item] = 0
                result += 1
            pass
        # print(result)
        # print(cache)
        # print(len(cache))
    print(result)
    return result
