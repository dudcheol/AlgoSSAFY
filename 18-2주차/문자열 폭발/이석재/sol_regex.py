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
