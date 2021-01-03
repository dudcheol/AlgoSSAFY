import sys
r=sys.stdin.readline
length=int(r())
board=list(map(int,r().split()))
count={}
for item in board:
    if item not in count:
        count[item]=1
    else:
        count[item]+=1
stack=[]
result=[0]*length
for index in range(length-1,-1,-1):
    while stack and count[stack[-1]]<=count[board[index]]:
        stack.pop()
    if stack:
        result[index]=stack[-1]
    else:
        result[index]=-1
    stack.append(board[index])
for item in result:
    print(item, end=" ")
