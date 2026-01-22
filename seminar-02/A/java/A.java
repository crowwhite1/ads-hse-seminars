import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine().trim());
        int[] a = new int[n];

        if (n > 0) {
            String[] parts = in.readLine().trim().split(" ");
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(parts[i]);
            }
        }

        long swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean changed = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                    changed = true;
                    swaps++;
                }
            }
            if (!changed) break;
        }

        if (n > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if (i > 0) sb.append(' ');
                sb.append(a[i]);
            }
            System.out.println(sb);
        } else {
            System.out.println();
        }

        System.out.println(swaps);
    }
}
