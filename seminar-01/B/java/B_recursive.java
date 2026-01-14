import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_recursive {
    static long[] memo;

    static long fibonacci(int n) {
        // Без memo рекурсивное решение работает слишком медленно
        if (memo[n] != -1) return memo[n];
        if (n == 0) return memo[n] = 0;
        if (n == 1) return memo[n] = 1;
        return memo[n] = fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine().trim());

        memo = new long[n + 1];
        for (int i = 0; i <= n; i++) memo[i] = -1;

        System.out.println(fibonacci(n));
    }
}