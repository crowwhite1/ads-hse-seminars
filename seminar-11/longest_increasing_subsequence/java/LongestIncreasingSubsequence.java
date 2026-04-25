import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LongestIncreasingSubsequence {

    public static List<Integer> longestIncreasingSubsequence(int[] a) {
        int n = a.length;
        int[] dp = new int[n];
        int[] prev = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            prev[i] = -1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (a[j] < a[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
        }

        int pos = 0;
        for (int i = 1; i < n; i++) {
            if (dp[i] > dp[pos]) {
                pos = i;
            }
        }

        List<Integer> ans = new ArrayList<>();
        while (pos != -1) {
            ans.add(a[pos]);
            pos = prev[pos];
        }

        Collections.reverse(ans);
        return ans;
    }
}
