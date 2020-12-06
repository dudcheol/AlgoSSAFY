# 시간초과
# 정규식탐색은 일반적인 replace나 선형탐색보다 빠르게 탐색했던 경험이 있어서 
import sys
import re

r = sys.stdin.readline

input_string = r().rstrip()
bomb_string = r().rstrip()
result = ""
while True:
    input_string = re.sub(bomb_string, "", input_string)
    if result == input_string:
        break
    result = input_string
print(result if result != "" else "FRULA")
