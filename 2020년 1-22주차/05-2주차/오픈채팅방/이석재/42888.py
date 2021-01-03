# https://programmers.co.kr/learn/courses/30/lessons/42888
def solution(record):
    dict1={}
    temp=[]
    result=[]
    for item in record:
        command, userid, *nickname=item.split(" ")
        nickname=nickname[0] if nickname else None

        if command=="Enter" or command=="Change":
            if userid not in dict1:
                dict1[userid]=nickname
            else:
                dict1[userid] = nickname
        temp.append([command, userid, nickname])
    for item in temp:
        command, userid, nickname = item[0],item[1],item[2]
        if command=="Enter":
            result.append(f"{dict1[userid]}님이 들어왔습니다.")
        elif command=="Leave":
            result.append(f"{dict1[userid]}님이 나갔습니다.")
    return result
