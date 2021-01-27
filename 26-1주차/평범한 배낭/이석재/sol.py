# 보석을 안넣거나 넣거나
# 보석을 넣었을때의 가치와 보석을 안넣었을때의 가치 중 큰 값으로 dp 테이블을 채워나간다
import sys

r = sys.stdin.readline

number, max_weight = map(int, r().split())
weights, values = [0], [0]
for i in range(number):
    a, b = map(int, r().split())
    weights.append(a)
    values.append(b)
dp = [[0] * (max_weight + 1) for _ in range(number + 1)]
for i in range(1, number + 1):
    for j in range(1, max_weight + 1):
        if weights[i] > j:
            dp[i][j] = dp[i - 1][j]
        else:
            dp[i][j] = max(dp[i - 1][j], dp[i-1][j - weights[i]] + values[i])
print(dp[-1][-1])
