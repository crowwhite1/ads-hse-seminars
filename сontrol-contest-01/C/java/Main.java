import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        long a, b, c;

        Node(long a, long b, long c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    static int upperBound(long[] arr, double x) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] <= x) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            String[] nm = br.readLine().split(" ");
            int n = Integer.parseInt(nm[0]);
            int m = Integer.parseInt(nm[1]);

            long[] k = new long[n];
            for (int i = 0; i < n; i++) {
                k[i] = Long.parseLong(br.readLine());
            }
            Arrays.sort(k);

            Node[] p = new Node[m];
            for (int i = 0; i < m; i++) {
                String[] s = br.readLine().split(" ");
                long a = Long.parseLong(s[0]);
                long b = Long.parseLong(s[1]);
                long c = Long.parseLong(s[2]);
                p[i] = new Node(a, b, c);
            }

            for (int i = 0; i < m; i++) {
                double val = 2.0 * Math.sqrt((double) p[i].a * p[i].c);
                double kx_m = p[i].b - val;
                double kx_p = p[i].b + val;

                int pos = upperBound(k, kx_m);
                if (pos != k.length) {
                    long ans = k[pos];
                    if (ans < kx_p) {
                        out.append("YES\n").append(ans).append('\n');
                    } else {
                        out.append("NO\n");
                    }
                } else {
                    out.append("NO\n");
                }
            }

            out.append('\n');
        }

        System.out.print(out);
    }
}