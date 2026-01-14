import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_list {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine().trim());

        // Решение не оптимально по памяти, так как хранится весь массив значений
        long[] fib = new long[Math.max(2, n + 1)];
        fib[0] = 0;
        fib[1] = 1;

        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }

        System.out.println(fib[n]);
    }
}
