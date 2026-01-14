import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class E {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(in.readLine().trim());

        List<String> parts = new ArrayList<>();

        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                int count = 0;
                while (n % i == 0) {
                    n /= i;
                    count++;
                }
                if (count == 1) {
                    parts.add(String.valueOf(i));
                } else {
                    parts.add(i + "^" + count);
                }
            }
        }

        if (n > 1) {
            parts.add(String.valueOf(n));
        }

        System.out.println(String.join("*", parts));
    }
}
