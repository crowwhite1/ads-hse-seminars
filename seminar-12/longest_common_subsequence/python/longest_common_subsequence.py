def longest_common_subsequence(x, y):
    """
    x, y — строки
    возвращает длину их наибольшей общей подпоследовательности
    """
    dp = [[0] * (len(y) + 1) for _ in range(len(x) + 1)]

    for i in range(len(x)):
        for j in range(len(y)):
            if x[i] == y[j]:
                dp[i + 1][j + 1] = dp[i][j] + 1
            else:
                dp[i + 1][j + 1] = max(dp[i][j + 1], dp[i + 1][j])

    return dp[len(x)][len(y)]
