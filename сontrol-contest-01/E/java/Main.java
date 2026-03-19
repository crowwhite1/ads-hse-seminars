import java.io.*;

public class Main {
    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
		     
        FastScanner(InputStream is) {
            in = is;
        }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' ');

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }
    }

    static class IntPairList {
        int[] x;
        int[] y;
        int size;

        IntPairList(int cap) {
            x = new int[cap];
            y = new int[cap];
        }

        void add(int a, int b) {
            if (size == x.length) grow();
            x[size] = a;
            y[size] = b;
            size++;
        }

        private void grow() {
            int newCap = x.length << 1;
            if (newCap == 0) newCap = 4;
            int[] nx = new int[newCap];
            int[] ny = new int[newCap];
            System.arraycopy(x, 0, nx, 0, size);
            System.arraycopy(y, 0, ny, 0, size);
            x = nx;
            y = ny;
        }
    }

    static final FastScanner fs = new FastScanner(System.in);
    static final StringBuilder out = new StringBuilder(1 << 20);

    static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    static boolean badTriple(int[] a) {
        return a[0] == 1 && a[1] == 3 && a[2] == 2
                || a[0] == 3 && a[1] == 1 && a[2] == 2
                || a[0] == 3 && a[1] == 2 && a[2] == 1;
    }

    static void testCase() throws Exception {
        int n = fs.nextInt();
        int[] a = new int[n];
        int[] p = new int[n + 1];

        for (int i = 0; i < n; i++) {
            int val = fs.nextInt();
            a[i] = val;
            p[val] = i;
        }

        if (n == 3 && badTriple(a)) {
            out.append(-1).append('\n');
            return;
        }

        int half = n >> 1;
        IntPairList ans = new IntPairList(Math.max(4, 4 * n + 10));

        int ind = p[1];

        while (ind < n - 1) {
            int target;
            if (ind + half < n) {
                target = ind + half;
                int val = a[target];
                ans.add(p[1] + 1, p[val] + 1);
                swap(p, 1, val);
                swap(a, p[1], p[val]);
                ind += half;
            } else {
                target = n - 1;
                int val = a[target];
                ans.add(p[1] + 1, p[val] + 1);
                swap(p, 1, val);
                swap(a, p[1], p[val]);
                ind = n - 1;
            }
        }

        int j = 1;
        while (ind > 0) {
            int to = p[ind + 1];
            int to2 = 0;

            while (ind > to) {
                if (ind - half > to) {
                    int v1 = a[ind];
                    int v2 = a[ind - half];
                    ans.add(p[v1] + 1, p[v2] + 1);
                    swap(p, v1, v2);
                    swap(a, p[v1], p[v2]);
                    ind -= half;
                } else {
                    int target = to;
                    int v1 = a[ind];
                    int v2 = a[target];
                    ans.add(p[v1] + 1, p[v2] + 1);
                    swap(p, v1, v2);
                    swap(a, p[v1], p[v2]);
                    to2 = p[a[ind]];
                    ind = to;
                }
            }

            while (ind <= n - j - 1) {
                if (ind < to2) {
                    if (ind == n - j - 1) {
                        j++;
                        break;
                    }

                    int shiftTarget = ind + (to2 - ind) - 1;

                    if (p[a[ind]] != p[a[shiftTarget]]) {
                        int v1 = a[ind];
                        int v2 = a[shiftTarget];
                        ans.add(p[v1] + 1, p[v2] + 1);
                        swap(p, v1, v2);
                        swap(a, p[v1], p[v2]);
                        ind = shiftTarget;
                    }

                    if (ind + half > n - j - 1) {
                        int target = n - j - 1;
                        if (p[a[ind]] != p[a[target]]) {
                            int v1 = a[ind];
                            int v2 = a[target];
                            ans.add(p[v1] + 1, p[v2] + 1);
                            swap(p, v1, v2);
                            swap(a, p[v1], p[v2]);
                            ind = target;
                        }
                    } else {
                        int target = ind + half;
                        int v1 = a[ind];
                        int v2 = a[target];
                        ans.add(p[v1] + 1, p[v2] + 1);
                        swap(p, v1, v2);
                        swap(a, p[v1], p[v2]);
                        ind += half;
                    }
                } else {
                    if (ind + 1 > n - j) {
                        int v1 = a[ind];
                        int v2 = a[to2];
                        ans.add(p[v1] + 1, p[v2] + 1);
                        swap(p, v1, v2);
                        swap(a, p[v1], p[v2]);

                        int tmp = ind;
                        ind = to2;
                        to2 = tmp;

                        v1 = a[ind];
                        v2 = a[to2 - 1];
                        ans.add(p[v1] + 1, p[v2] + 1);
                        swap(p, v1, v2);
                        swap(a, p[v1], p[v2]);

                        ind = n - j - 1;
                        j++;
                        break;
                    } else {
                        int v1 = a[ind];
                        int v2 = a[ind + 1];
                        ans.add(p[v1] + 1, p[v2] + 1);
                        swap(p, v1, v2);
                        swap(a, p[v1], p[v2]);

                        ind++;

                        v1 = a[ind];
                        v2 = a[to2];
                        ans.add(p[v1] + 1, p[v2] + 1);
                        swap(p, v1, v2);
                        swap(a, p[v1], p[v2]);

                        int tmp = ind;
                        ind = to2;
                        to2 = tmp;
                    }
                }
            }
        }

        int m = ans.size;

        if (m == 0) {
            out.append(0).append('\n');
            return;
        }

        if (m == 1) {
            out.append(1).append('\n');
            out.append(ans.x[0]).append(' ').append(ans.y[0]).append('\n');
            return;
        }

        IntPairList res = new IntPairList(m);
        for (int i = 0; i < m - 1; i++) {
            if (ans.x[i] == ans.y[i + 1] && ans.y[i] == ans.x[i + 1]) {
                i++;
            } else {
                res.add(ans.x[i], ans.y[i]);
            }
        }

        if (ans.x[m - 2] != ans.y[m - 1] || ans.y[m - 2] != ans.x[m - 1]) {
            res.add(ans.x[m - 1], ans.y[m - 1]);
        }

        if (res.size > 4 * n) {
            out.append(-1).append('\n');
        } else {
            out.append(res.size).append('\n');
            for (int i = 0; i < res.size; i++) {
                out.append(res.x[i]).append(' ').append(res.y[i]).append('\n');
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int t = fs.nextInt();
        while (t-- > 0) {
            testCase();
        }
        System.out.print(out);
    }
}