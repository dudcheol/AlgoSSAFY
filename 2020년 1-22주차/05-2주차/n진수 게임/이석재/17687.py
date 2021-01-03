# https://programmers.co.kr/learn/courses/30/lessons/17687
# t*m 길이의 문자열을 만들고,
# 거기서 (p-1) + i*m 번째 인덱스의 값을 붙여서 만든 문자열을 반환한다,(0<=i<t)
# 재귀로 n 진법에 해당하는 0~ x 까지의 자연수를 이어서 계속 붙인 문자열을 반환하는 함수를 만들고
# 그 함수는 t*m의 길이 이상이 되면 문자열을 반환하는데 그 문자열이 t*m 이다.
def change(num,decimal):
    str1=""
    target=num
    while True:
        if 15>=target%decimal>=10:
            str1+=chr(65+(target%decimal)%10)
        else:
            str1+=str(target%decimal)
        target=target//decimal
        if target >= decimal: continue
        break
    if target!=0:
        str1+=str(chr(65+(target)%10)) if 10<=target<=15 else str(target)
    return str1[::-1]

def solution(n,t,m,p):
    str1=""
    result=""
    i=0
    while len(str1)<t*m:
        str1+=change(i,n)
        # print(i, change(i,n))
        i+=1
    for i in range(t):
        result+=str1[p-1+i*m]
    return result
