# 안일하게 인덱싱을 하면 망함
# 1번 사람을 표현하기 위에 list에 [0]을 넣고 시작할땐,
# 이 로직이 과연 맞는지 한번만 확인하자
# 본방에서 이게 문제가 될거라고 생각하지 않기 때문에 엄청 말릴 수 있음

from collections import deque
from heapq import *

test_case_num = int(input())
for t in range(test_case_num):
    table1_length, table2_length, person_num, target1, target2 = map(int, input().split())
    table1 = [0] + list(map(int, input().split()))
    table2 = [0] + list(map(int, input().split()))
    waiting_time = list(map(int, input().split()))

    visited1, visited2 = set(), set()
    result = {}
    table1_dict, table2_dict = {}, {}
    for i in range(1, table1_length + 1):
        table1_dict[i] = []
    for i in range(1, table2_length + 1):
        table2_dict[i] = []
    table1_cur_length, table2_cur_length = 0, 0
    table1_waiting = deque([])
    table2_waiting = deque([])

    waiting_time.sort()
    temp_index = [i for i in range(1, person_num + 1)]
    indexed_waiting_time = list(map(lambda x, y: (x, y), waiting_time, temp_index))
    indexed_waiting_time.sort()
    indexed_waiting_time = deque(indexed_waiting_time)

    time = 0
    # 접수 데스크, 정비 데스
    while len(visited2) != person_num:
        # 이번 타임에 나와야 하는 사람 목록
        candis = []
        while indexed_waiting_time and indexed_waiting_time[0][0] == time:
            candis.append(indexed_waiting_time.popleft())
        # 일단 접수 데스크 대기 리스트에 넣음
        candis.sort()
        for candi in candis:
            table1_waiting.append(candi)
        # 접수 데스크 안에 사람이 있으면 그 시간을 1씩 올려줌
        # print(f"접수대기현황 {table1_waiting}")
        temp_candis = []
        for key in table1_dict:
            if len(table1_dict[key]) != 0:
                table1_dict[key] = [table1_dict[key][0], table1_dict[key][1] + 1]
                # 만일 그 시간이 다 되었다면 정비데스크 대기리스트에 넣어주고 테이블을 비운다.
                if table1_dict[key][1] == table1[key]:
                    candi = table1_dict[key][0]
                    temp_candis.append((key, candi))
                    table1_dict[key] = []
                    table1_cur_length -= 1
                    # print(f"접수테이블 {key} 번 빠짐")
        temp_candis.sort()
        for candi in temp_candis:
            table2_waiting.append(candi[1])
        # 정비 데스크 안에 사람이 있으면 그 시간을 1씩 올려줌.
        temp_candis = []
        for key in table2_dict:
            if len(table2_dict[key]) != 0:
                table2_dict[key] = [table2_dict[key][0], table2_dict[key][1] + 1]
                # 만일 그 시간이 다 되었다면 테이블을 비운다. 별거 처리 할 필욘 없
                if table2_dict[key][1] == table2[key]:
                    candi = table2_dict[key][0]
                    temp_candis.append(candi)
                    table2_dict[key] = []
                    table2_cur_length -= 1
                    # print(f"수리테이블 {key} 번 빠짐")
        # 이제 접수, 정비 데스크에 대기해있는 사람을 보면서 테이블에 넣을 수 있으면 넣어보자
        # dict 이 기본적으로 key 값 순서대로 훝기 때문에 괜찮음
        while table1_waiting and table1_length > table1_cur_length:
            candi = table1_waiting.popleft()
            for key in table1_dict:
                if len(table1_dict[key]) == 0:
                    table1_dict[key] = [candi[1], 0]
                    table1_cur_length += 1
                    # print(f"{candi[1]} 접수 도착")
                    visited1.add(candi[1])
                    result[candi[1]] = [key]
                    break
        while table2_waiting and table2_length > table2_cur_length:
            candi = table2_waiting.popleft()
            for key in table2_dict:
                if len(table2_dict[key]) == 0:
                    table2_dict[key] = [candi, 0]
                    table2_cur_length += 1
                    # print(f"{candi} 정비 도착")
                    visited2.add(candi)
                    result[candi].append(key)
                    break
        time += 1
    tt = 0
    for key in result:
        if result[key] == [target1, target2]:
            tt += key
    if tt == 0:
        tt = -1
    print(f"#{t + 1} {tt}")
