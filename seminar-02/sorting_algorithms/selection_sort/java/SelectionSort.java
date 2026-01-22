import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SelectionSort {
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

        for (int i = 0; i < n; i++) {
            int minPos = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[minPos]) {
                    minPos = j;
                }
            }
            int tmp = a[i];
            a[i] = a[minPos];
            a[minPos] = tmp;
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
    }
}
