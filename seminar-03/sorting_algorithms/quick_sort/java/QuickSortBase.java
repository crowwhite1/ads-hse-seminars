import java.io.BufferedReader;
import java.io.InputStreamReader;

public class QuickSortBase {

    static void quickSort(int[] a, int l, int r) {
        if (r - l < 2) return;

        int pivot = a[(l + r) >>> 1]; // детерминированный pivot
        int i = l;
        int j = l;

        // a[l:i)  - < pivot
        // a[i:j)  - == pivot
        // a[j:r)  - > pivot
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

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];

        if (n > 0) {
            String[] parts = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(parts[i]);
            }
        }

        quickSort(a, 0, n);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(' ');
            sb.append(a[i]);
        }
        System.out.println(sb);
    }
}
