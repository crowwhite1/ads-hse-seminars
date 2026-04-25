def longest_increasing_subsequence(a):
    """
    a — список чисел
    возвращает одну из наибольших возрастающих подпоследовательностей
    """
    n = len(a)
    dp = [1] * n
    prev = [-1] * n

    for i in range(n):
        for j in range(i):
            if a[j] < a[i] and dp[j] + 1 > dp[i]:
                dp[i] = dp[j] + 1
                prev[i] = j

    pos = 0
    for i in range(1, n):
        if dp[i] > dp[pos]:
            pos = i

    ans = []
    while pos != -1:
        ans.append(a[pos])
        pos = prev[pos]

    ans.reverse()
    return ans
