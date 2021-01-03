# https://www.acmicpc.net/problem/1715

# 반례1
# 정렬하고 앞에서부터 2개씩 끊어서 더해주면
# 배열의 길이가 홀수개일 때 더한 배열의 결과가 오름차순 정렬이 아닐 수 있음

# 반례2
# 두 묶음을 한 묶음으로 만드는 행위 후에 항상 최소의 묶음 수를 뽑아서 더해야 결국 최소가됨
# 그런데 우선순위큐를 사용하지 않고는 묶음을 만드는 행위를 한 뒤에 위 조건을 보장하기 위해서
# 매번 정렬을 해줘야하고, 일반적으로 내장 정렬 시간복잡도가 nlogn 인 것을 고려해볼때
# 우선순위 큐 쓰는게 낫다

import sys
from heapq import *

r = sys.stdin.readline
length = int(r())
board = []
for i in range(length):
    board.append(int(r()))
heapify(board)

result = 0
while len(board) > 1:
    t1, t2 = heappop(board), heappop(board)
    result += (t1 + t2)
    heappush(board, t1 + t2)

print(result)
