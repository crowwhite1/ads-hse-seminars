import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class B {

    // первый индекс >= target
    static int lbound(int target, int[] a) {
        int l = -1;
        int r = a.length;
        while (r - l > 1) {
            int m = (l + r) >>> 1;
            if (a[m] >= target) {
                r = m;
            } else {
                l = m;
            }
        }
        return r;
    }

    // последний индекс <= target
    static int rbound(int target, int[] a) {
        int l = -1;
        int r = a.length;
        while (r - l > 1) {
            int m = (l + r) >>> 1;
            if (a[m] > target) {
                r = m;
            } else {
                l = m;
            }
        }
        return l;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] first = in.readLine().split(" ");
        int n = Integer.parseInt(first[0]);
        int k = Integer.parseInt(first[1]);

        int[] a = new int[n];
        String[] arrA = in.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(arrA[i]);
        }

        String[] arrB = in.readLine().split(" ");
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < k; i++) {
            int target = Integer.parseInt(arrB[i]);

            int l = lbound(target, a);
            int r = rbound(target, a);

            if (l <= r) {
                out.append(target).append('\n');
            } else if (l >= n) {
                out.append(a[r]).append('\n');
            } else if (r < 0) {
                out.append(a[l]).append('\n');
            } else {
                if (a[l] - target < target - a[r]) {
                    out.append(a[l]).append('\n');
                } else {
                    out.append(a[r]).append('\n');
                }
            }
        }

        System.out.print(out.toString());
    }
}
