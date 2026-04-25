#include <vector>
#include <algorithm>

using namespace std;

struct KnapsackResult {
    long long total_cost;
    long long total_weight;
    vector<int> indices;
};

KnapsackResult knapsack(int capacity, const vector<int> &weights, const vector<int> &costs) {
    int n = (int) weights.size();
    vector<vector<long long>> dp(n + 1, vector<long long>(capacity + 1, 0));

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= capacity; j++) {
            if (weights[i - 1] > j) {
                dp[i][j] = dp[i - 1][j];
            } else {
                dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + costs[i - 1]);
            }
        }
    }

    int i = n;
    int j = capacity;
    vector<int> indices;

    while (i != 0 && j != 0) {
        if (weights[i - 1] > j) {
            i--;
        } else if (dp[i - 1][j] > dp[i - 1][j - weights[i - 1]] + costs[i - 1]) {
            i--;
        } else {
            indices.push_back(i - 1);
            j -= weights[i - 1];
            i--;
        }
    }

    long long total_cost = 0;
    long long total_weight = 0;
    for (int id : indices) {
        total_cost += costs[id];
        total_weight += weights[id];
    }

    return {total_cost, total_weight, indices};
}
