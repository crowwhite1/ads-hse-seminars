import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C_for_else {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(in.readLine().trim());

        boolean foundDivisor = false;

        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                foundDivisor = true;
                System.out.println("composite");
                break;
            }
        }

        // Аналог блока else в Python (если break не сработал)
        if (!foundDivisor) {
            System.out.println("prime");
        }
    }
}

