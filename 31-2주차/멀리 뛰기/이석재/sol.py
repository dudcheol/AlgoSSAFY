# 얘들아 문제 잘못 골랐어~

# 1칸 혹은 2칸만 뛸 수 있음, 1234567을 나눈 값을 반환한다.
# 생각해보자
# 뭔가 dp 문제일 것 같다
# 현재 위치를 k라고 하자
# 현재 위치는 이전 위치 -2 에서 오거나 이전 위치 -1 에서 올 수 있다.

def solution(n):
    dp = []
    for i in range(n + 1):
        dp.append([0, 0])
    if n == 1:
        return 1
    if n == 2:
        return 2
    else:
        dp[1] = [1, 0]
        dp[2] = [1, 1]
        for i in range(3, n + 1):
            dp[i][0] = sum(dp[i - 1])
            dp[i][1] = sum(dp[i - 2])
        return sum(dp[n]) % 1234567
