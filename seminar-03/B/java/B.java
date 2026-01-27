import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine().trim());
        int[] a = new int[n];

        if (n > 0) {
            String[] parts = in.readLine().trim().split("\\s+");
            for (int i = 0; i < n; i++) a[i] = Integer.parseInt(parts[i]);
        }

        int[] buf = new int[n];
        mergeSort(a, buf, 0, n);

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) out.append(' ');
            out.append(a[i]);
        }
        System.out.print(out);
    }

    static void mergeSort(int[] a, int[] buf, int l, int r) {
        if (r - l < 2) return;
        int m = (l + r) >>> 1;
        mergeSort(a, buf, l, m);
        mergeSort(a, buf, m, r);
        merge(a, buf, l, m, r);
    }

    static void merge(int[] a, int[] buf, int l, int m, int r) {
        int i = l, j = m, k = 0;

        while (i < m || j < r) {
            if (i == m) buf[k++] = a[j++];
            else if (j == r) buf[k++] = a[i++];
            else if (a[i] <= a[j]) buf[k++] = a[i++]; // стабильность
            else buf[k++] = a[j++];
        }

        for (int t = 0; t < k; t++) a[l + t] = buf[t];
    }
}
