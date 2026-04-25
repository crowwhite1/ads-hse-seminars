n = int(input())
dp = [0] * (n + 1)
dp[n] = 1
dp[n - 1] = 1
dp[n - 2] = 2
for i in range(n - 3, -1, -1):
    dp[i] = (dp[i + 1] + dp[i + 2] + dp[i + 3]) % (10**9 + 7)
print(dp[0])