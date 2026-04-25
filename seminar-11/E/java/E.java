import java.io.*;
import java.util.*;

public class E {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(input[i]);
        }
        
        int[] dp = new int[n];
        int[] prev = new int[n];
        
        Arrays.fill(dp, 1);
        Arrays.fill(prev, -1);
        
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
        for (int i = 0; i < ans.size(); i++) {
            System.out.print(ans.get(i));
            if (i < ans.size() - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}