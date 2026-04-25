import java.io.*;

public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];
        dp[n] = 1;
        dp[n - 1] = 1;
        dp[n - 2] = 2;
        
        for (int i = n - 3; i >= 0; i--) {
            dp[i] = (int)((dp[i + 1] + dp[i + 2] + dp[i + 3]) % (Math.pow(10, 9) + 7));
        }
        
        System.out.println(dp[0]);
    }
}