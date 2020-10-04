#https://programmers.co.kr/learn/courses/30/lessons/17682
def solution(dartResult):
    parse=[]
    i=0
    while (len(dartResult)>0):
        if dartResult[i]=="S" or dartResult[i]=="D" or dartResult[i]=="T":
            parse.append(dartResult[:i+1])
            dartResult=dartResult[i+1:]
            i=0
        elif dartResult[i]=="*" or dartResult[i]=="#":
            parse.append(dartResult[i])
            dartResult=dartResult[i+1:]
            i=0
        else:
            i+=1
    print(parse)
    result=0
    for i in range(len(parse)):
        if parse[i][-1]=="S":
            parse[i]=int(parse[i][:len(parse[i])-1])
        elif parse[i][-1]=="D":
            parse[i]=pow(int(parse[i][:len(parse[i])-1]),2)
        elif parse[i][-1] == "T":
            parse[i] = pow(int(parse[i][:len(parse[i])-1]), 3)
    print(parse)
    # len_parse=len(parse)
    i=0
    while i<len(parse):
        if parse[i]=="*":
            for j in range(i-1,i-3,-1):
                if j>=0:
                    parse[j]=parse[j]*2
                pass
            del parse[i]
            i+=1
        elif parse[i]=="#":
            for j in range(i-1,i-2,-1):
                if j>=0:
                    parse[j]=-parse[j]
            del parse[i]
            i+=1
        else:
            i+=1
    print(parse)
    # print(sum(parse))
    return(sum(parse))

# solution("1S2D*3T")
# solution("1S*2T*3S")
