import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {

    // first index i such that a[i] >= target (0..n)
    static int lbound(int[] a, int target) {
        int l = -1, r = a.length;
        while (r - l > 1) {
            int m = l + (r - l) / 2;
            if (a[m] >= target) r = m;
            else l = m;
        }
        return r;
    }

    // last index i such that a[i] <= target  (or -1 if all > target)
    static int rbound(int[] a, int target) {
        int l = -1, r = a.length;
        while (r - l > 1) {
            int m = l + (r - l) / 2;
            if (a[m] > target) r = m;
            else l = m;
        }
        return l;
    }

    static void readArray(BufferedReader in, int[] a) throws IOException {
        String[] s = in.readLine().split(" ");
        for (int i = 0; i < a.length; i++) {
            a[i] = Integer.parseInt(s[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] s = in.readLine().split(" ");

        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);

        int[] a = new int[n];
        int[] b = new int[m];
        readArray(in, a);
        readArray(in, b);

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int target = b[i];
            int l = lbound(a, target);
            int r = rbound(a, target);
            if (l > r) {
                out.append("0\n");
            } else {
                // 1-based output
                out.append(l + 1).append(' ').append(r + 1).append('\n');
            }
        }
        System.out.print(out.toString());
    }
}
