import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MergeSort {

    static void merge(List<Integer> a, int l, int m, int r) {
        List<Integer> buf = new ArrayList<>(r - l);
        int i = l, j = m;

        while (i < m || j < r) {
            if (i == m) {
                buf.add(a.get(j++));
            } else if (j == r) {
                buf.add(a.get(i++));
            } else if (a.get(i) <= a.get(j)) { // <= для устойчивости
                buf.add(a.get(i++));
            } else {
                buf.add(a.get(j++));
            }
        }

        for (int k = 0; k < buf.size(); k++) {
            a.set(l + k, buf.get(k));
        }
    }

    static void mergeSort(List<Integer> a, int l, int r) {
        if (r - l < 2) return;
        int m = (l + r) / 2;
        mergeSort(a, l, m);
        mergeSort(a, m, r);
        merge(a, l, m, r);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine());
        List<Integer> a = new ArrayList<>(n);

        if (n > 0) {
            String[] parts = in.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                a.add(Integer.parseInt(parts[i]));
            }
        }

        mergeSort(a, 0, n);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(' ');
            sb.append(a.get(i));
        }
        System.out.println(sb);
    }
}
