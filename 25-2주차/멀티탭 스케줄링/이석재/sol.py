# 나는 조건 순서가
# 현재시점에서 앞으로 사용해야할 횟수가 작은 것부터 빼고
# 만약에 횟수가 같을 경우에는 나중에 사용하는 것을 빼줬음
# 그런데 이렇게 하면 
# 2 15
# 3 2 1 2 1 2 1 2 1 3 3 3 3 3 3
# 이걸 통과 못함
# 횟수가 작은것부터 빼는게 아니라 그냥 나중에 쓰는것부터 빼면됨
# 왜 이게 맞는지에 대한 당위성은 모르겠음

import sys
from collections import deque

r = sys.stdin.readline

plug_length, sequence_length = map(int, r().split())
sequence = list(map(int, r().split()))

dict1 = {}
for index, item in enumerate(sequence):
    try:
        dict1[item].append(index)
    except Exception:
        dict1[item] = deque([index])

plug, count = set(), 0

for item in sequence:
    if item in plug:
        dict1[item].popleft()
        pass
    else:
        if len(plug) < plug_length:
            plug.add(item)
            dict1[item].popleft()
        else:
            count += 1
            target = None
            for item2 in plug:
                if target is None:
                    target = item2
                else:
                    if not dict1[item2]:
                        target = item2
                    else:
                        if dict1[target]:
                            target = item2 if dict1[item2][0] > dict1[target][0] else target
            plug.remove(target)
            plug.add(item)
            dict1[item].popleft()
print(count)
