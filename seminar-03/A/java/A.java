import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine().trim());

        long a = 1;
        long b = 1;
        long current = 0;

        for (int i = 0; i < n; i++) {
            long aVal = a * a;
            long bVal = b * b * b;

            if (aVal < bVal) {
                current = aVal;
                a++;
            } else if (aVal > bVal) {
                current = bVal;
                b++;
            } else {
                current = aVal;
                a++;
                b++;
            }
        }

        System.out.println(current);
    }
}
