public class LongestCommonSubsequence {

    public static int longestCommonSubsequence(String x, String y) {
        int[][] dp = new int[x.length() + 1][y.length() + 1];

        for (int i = 0; i < x.length(); i++) {
            for (int j = 0; j < y.length(); j++) {
                if (x.charAt(i) == y.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }

        return dp[x.length()][y.length()];
    }
}
