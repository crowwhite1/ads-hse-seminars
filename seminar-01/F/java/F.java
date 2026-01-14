import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class F {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(in.readLine().trim());

        int limit = 2_000_000; // гарантированно хватает для k <= 100000

        boolean[] isPrime = new boolean[limit + 1];
        for (int i = 0; i <= limit; i++) isPrime[i] = true;
        isPrime[0] = false;
        isPrime[1] = false;

        for (int p = 2; p * p <= limit; p++) {
            if (isPrime[p]) {
                for (int m = p * p; m <= limit; m += p) {
                    isPrime[m] = false;
                }
            }
        }

        int count = 0;
        for (int x = 2; x <= limit; x++) {
            if (isPrime[x]) {
                count++;
                if (count == k) {
                    System.out.println(x);
                    break;
                }
            }
        }
    }
}
