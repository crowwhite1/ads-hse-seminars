import java.util.ArrayList;
import java.util.List;

public class Knapsack {

    public static class KnapsackResult {
        long totalCost;
        long totalWeight;
        List<Integer> indices;

        KnapsackResult(long totalCost, long totalWeight, List<Integer> indices) {
            this.totalCost = totalCost;
            this.totalWeight = totalWeight;
            this.indices = indices;
        }
    }

    public static KnapsackResult knapsack(int capacity, int[] weights, int[] costs) {
        int n = weights.length;
        long[][] dp = new long[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (weights[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + costs[i - 1]);
                }
            }
        }

        int i = n;
        int j = capacity;
        List<Integer> indices = new ArrayList<>();

        while (i != 0 && j != 0) {
            if (weights[i - 1] > j) {
                i--;
            } else if (dp[i - 1][j] > dp[i - 1][j - weights[i - 1]] + costs[i - 1]) {
                i--;
            } else {
                indices.add(i - 1);
                j -= weights[i - 1];
                i--;
            }
        }

        long totalCost = 0;
        long totalWeight = 0;
        for (int id : indices) {
            totalCost += costs[id];
            totalWeight += weights[id];
        }

        return new KnapsackResult(totalCost, totalWeight, indices);
    }
}
