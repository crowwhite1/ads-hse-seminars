import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MergeSortOptimized {

    static void sort(int[] a, int[] tmp, int l, int r) {
        if (r - l < 2) return;

        int m = (l + r) / 2;
        sort(a, tmp, l, m);
        sort(a, tmp, m, r);

        int i = l, j = m, k = l;
        while (i < m || j < r) {
            if (i == m) {
                tmp[k++] = a[j++];
            } else if (j == r) {
                tmp[k++] = a[i++];
            } else if (a[i] <= a[j]) { // <= для устойчивости
                tmp[k++] = a[i++];
            } else {
                tmp[k++] = a[j++];
            }
        }

        for (int t = l; t < r; t++) {
            a[t] = tmp[t];
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine());
        int[] a = new int[n];

        if (n > 0) {
            String[] parts = in.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(parts[i]);
            }
        }

        int[] tmp = new int[n];
        sort(a, tmp, 0, n);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(' ');
            sb.append(a[i]);
        }
        System.out.println(sb);
    }
}
