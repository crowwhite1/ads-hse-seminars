import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B {
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

        int count = 0;

        for (int i = 1; i < n; i++) {
            int key = a[i];
            int j = i - 1;
            boolean moved = false;

            while (j >= 0 && a[j] > key) {
                a[j + 1] = a[j];
                j--;
                moved = true;
            }

            a[j + 1] = key;

            if (!moved) {
                count++;
            }
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

        System.out.println(count);
    }
}
