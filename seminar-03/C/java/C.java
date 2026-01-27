import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine().trim());
        int[] a = new int[n];

        if (n > 0) {
            String[] parts = in.readLine().trim().split("\\s+");
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(parts[i]);
            }
        }

        quickSort(a, 0, n);

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) out.append(' ');
            out.append(a[i]);
        }
        System.out.print(out);
    }

    static void quickSort(int[] a, int l, int r) {
        if (r - l < 2) return;

        int pivot = a[(l + r) >>> 1];
        int i = l;
        int j = l;

        for (int k = l; k < r; k++) {
            if (a[k] < pivot) {
                swap(a, k, i);
                if (j != i) swap(a, k, j);
                i++;
                j++;
            } else if (a[k] == pivot) {
                swap(a, k, j);
                j++;
            }
        }

        quickSort(a, l, i);
        quickSort(a, j, r);
    }

    static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
