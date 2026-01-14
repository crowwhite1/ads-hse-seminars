import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G {

    static int gcd(int a, int b) {
        // Алгоритм Евклида
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine().trim());
        String[] parts = in.readLine().split(" ");

        int g = Integer.parseInt(parts[0]);
        for (int i = 1; i < n; i++) {
            g = gcd(g, Integer.parseInt(parts[i]));
        }

        System.out.println(g);
    }
}
