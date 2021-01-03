# 스택사용
# 자료구조 및 문자열의 길이 등을 변수에 담아서 사용
import sys
from collections import deque

r = sys.stdin.readline

target_str, pattern_str = r().rstrip(), r().rstrip()

target_list, pattern_length = deque(list(target_str)), len(pattern_str)
stack = []
stack_length = len(stack)
while target_list:
    stack.append(target_list.popleft())
    stack_length += 1
    if stack_length >= pattern_length:
        if "".join(stack[stack_length - pattern_length:]) == pattern_str:
            count = 0
            while count < pattern_length:
                stack.pop()
                count += 1
                stack_length -= 1
print("".join(stack) if stack else "FRULA")
