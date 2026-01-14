import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine().trim());

        long a = 0, b = 1;
        for (int i = 0; i < n; i++) {
            long tmp = a + b;
            a = b;
            b = tmp;
        }

        System.out.println(a);
    }
}