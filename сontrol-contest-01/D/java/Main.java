import java.io.*;
import java.util.*;

public class Main {

    static class Pair {
        long first;
        int second;

        Pair(long first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    static void testCase(BufferedReader br) throws Exception {
        String[] firstLine = br.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        long k = Long.parseLong(firstLine[1]);

        String[] arr = br.readLine().split(" ");

        Pair[] a = new Pair[n];
        long mxAns = 0;

        for (int i = 0; i < n; i++) {
            long x = Long.parseLong(arr[i]);
            a[i] = new Pair(x, i);
            mxAns = Math.max(mxAns, x);
        }

        Arrays.sort(a, (p1, p2) -> {
            if (p1.first != p2.first) {
                return Long.compare(p1.first, p2.first);
            }
            return Integer.compare(p1.second, p2.second);
        });

        int j = 1;
        long mx = a[0].first;
        long sm = 0;

        while (k > 0 && j < n) {
            long cost = (a[j].first - a[j - 1].first) * j;
            if (cost + sm <= k) {
                sm += cost;
                mx = a[j].first;
            } else {
                break;
            }
            j++;
        }

        for (int i = 0; i < j; i++) {
            a[i].first = mx;
        }

        StringBuilder out = new StringBuilder();

        if (j <= n - 1) {
            long L = 0;
            long R = k - sm + 1;

            while (R - L > 1) {
                long m = (L + R) / 2;
                if (m * j <= k - sm) {
                    L = m;
                } else {
                    R = m;
                }
            }

            for (int i = 0; i < j; i++) {
                a[i].first += L;
            }

            out.append(mxAns - L - mx).append('\n');
        }

        Arrays.sort(a, Comparator.comparingInt(p -> p.second));

        if (n == j) {
            out.append(mxAns - mx).append('\n');
        }

        for (int i = 0; i < n; i++) {
            out.append(a[i].first).append(' ');
        }

        System.out.print(out);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        testCase(br);
    }
}