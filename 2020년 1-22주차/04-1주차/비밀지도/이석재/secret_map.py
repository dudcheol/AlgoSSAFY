#https://programmers.co.kr/learn/courses/30/lessons/17681
def solution(n, arr1, arr2):
    result=[]
    # print(bin(9|30)[2:])
    for i in range(n):
        target=bin(arr1[i]|arr2[i])[2:]
        if len(target)<n:
            target="0"*(n-len(target))+target
        target=target.replace("1","#").replace("0"," ")
        result.append(target)
    print(result)
    return result
