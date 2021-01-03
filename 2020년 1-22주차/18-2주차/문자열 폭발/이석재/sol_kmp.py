# 시간초과
# KMP는 가능한 시간복잡도로 질문게시판에서 보았던 것 같은데
# PYTHON이여서 그게 느린건지, 내 코드가 비효율적인지 모르겠지만 시간초과 걸림
import sys

r = sys.stdin.readline


def lps(pattern_str):
    pattern_len = len(pattern_str)
    lps_list = [0] * pattern_len
    j = 0

    for i in range(1, pattern_len):
        while j > 0 and pattern_str[i] != pattern_str[j]:
            j = lps_list[j - 1]

        if pattern_str[i] == pattern_str[j]:
            j += 1
            lps_list[i] = j
    return lps_list


def kmp(pattern_str, target_str, lps_list):
    j = 0
    result = []
    pattern_len = len(pattern_str)
    target_len = len(target_str)
    result_string = target_str
    for i in range(target_len):
        while j > 0 and target_str[i] != pattern_str[j]:
            j = lps_list[j - 1]
        if target_str[i] == pattern_str[j]:
            if j == pattern_len - 1:
                result.append(i - pattern_len + 1)
                j = lps_list[j]
            else:
                j += 1
    return result


target_str = r().rstrip()
pattern_str = r().rstrip()

lps_list = lps(pattern_str)
gap = 0
pattern_length = len(pattern_str)


def explode(target_str, pattern_length, index):
    return target_str[:index] + target_str[index + pattern_length:]


while True:
    result = kmp(pattern_str, target_str, lps_list)
    # print(result)
    if len(result) == 0:
        break
    for item in result:
        target_str = explode(target_str, pattern_length, item - gap)
        gap += pattern_length
    gap = 0
    # print(target_str)
print(target_str if target_str != "" else "FRULA")
