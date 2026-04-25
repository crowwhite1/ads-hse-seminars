def knapsack(capacity, weights, costs):
    """
    capacity — максимальный допустимый вес
    weights — веса предметов
    costs — стоимости предметов
    возвращает (total_cost, total_weight, indices)
    """
    n = len(weights)
    dp = [[0] * (capacity + 1) for _ in range(n + 1)]

    for i in range(1, n + 1):
        for j in range(1, capacity + 1):
            if weights[i - 1] > j:
                dp[i][j] = dp[i - 1][j]
            else:
                dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + costs[i - 1])

    i = n
    j = capacity
    indices = []

    while i != 0 and j != 0:
        if weights[i - 1] > j:
            i -= 1
        elif dp[i - 1][j] > dp[i - 1][j - weights[i - 1]] + costs[i - 1]:
            i -= 1
        else:
            indices.append(i - 1)
            j -= weights[i - 1]
            i -= 1

    total_cost = sum(costs[i] for i in indices)
    total_weight = sum(weights[i] for i in indices)
    return total_cost, total_weight, indices
