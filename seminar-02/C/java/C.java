import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C {
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

        for (int i = 1; i < n; i++) {
            int tmp = a[i];
            int j = i - 1;
            boolean moved = false;

            while (j >= 0 && a[j] > tmp) {
                a[j + 1] = a[j];
                j--;
                moved = true;
            }
            a[j + 1] = tmp;

            if (moved) {
                StringBuilder sb = new StringBuilder();
                for (int k = 0; k < n; k++) {
                    if (k > 0) sb.append(' ');
                    sb.append(a[k]);
                }
                System.out.println(sb);
            }
        }
    }
}
