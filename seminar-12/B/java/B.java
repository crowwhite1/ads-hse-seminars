import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class B {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int w = Integer.parseInt(br.readLine());

        String[] pLine = br.readLine().split(" ");
        String[] cLine = br.readLine().split(" ");

        int[] p = new int[n];
        int[] c = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = Integer.parseInt(pLine[i]);
            c[i] = Integer.parseInt(cLine[i]);
        }

        long[][] dp = new long[n + 1][w + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= w; j++) {
                if (p[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - p[i - 1]] + c[i - 1]);
                }
            }
        }

        int i = n;
        int j = w;
        List<Integer> backpack = new ArrayList<>();

        while (i != 0 && j != 0) {
            if (p[i - 1] > j) {
                i--;
            } else {
                if (dp[i - 1][j] > dp[i - 1][j - p[i - 1]] + c[i - 1]) {
                    i--;
                } else {
                    backpack.add(i);
                    j -= p[i - 1];
                    i--;
                }
            }
        }

        long totalCost = 0;
        long totalWeight = 0;
        for (int id : backpack) {
            totalCost += c[id - 1];
            totalWeight += p[id - 1];
        }

        StringBuilder out = new StringBuilder();
        out.append(totalCost).append('\n');
        out.append(totalWeight).append('\n');
        out.append(backpack.size()).append('\n');

        for (int k = 0; k < backpack.size(); k++) {
            if (k > 0) {
                out.append(' ');
            }
            out.append(p[backpack.get(k) - 1]);
        }
        out.append('\n');

        for (int k = 0; k < backpack.size(); k++) {
            if (k > 0) {
                out.append(' ');
            }
            out.append(c[backpack.get(k) - 1]);
        }
        out.append('\n');

        System.out.print(out);
    }
}
