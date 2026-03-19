import java.io.InputStream;
import java.io.IOException;

public class Main {
    static final class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 20];
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

    static final class IntPairList {
        int[] x;
        int[] y;
        int size;

        IntPairList(int cap) {
            x = new int[Math.max(cap, 4)];
            y = new int[Math.max(cap, 4)];
        }

        void add(int a, int b) {
            if (size == x.length) grow();
            x[size] = a;
            y[size] = b;
            size++;
        }

        private void grow() {
            int newCap = x.length << 1;
            int[] nx = new int[newCap];
            int[] ny = new int[newCap];
            System.arraycopy(x, 0, nx, 0, size);
            System.arraycopy(y, 0, ny, 0, size);
            x = nx;
            y = ny;
        }
    }

    static final FastScanner fs = new FastScanner(System.in);
    static final StringBuilder out = new StringBuilder(1 << 22);

    static boolean badTriple(int[] a) {
        return (a[0] == 1 && a[1] == 3 && a[2] == 2)
                || (a[0] == 3 && a[1] == 1 && a[2] == 2)
                || (a[0] == 3 && a[1] == 2 && a[2] == 1);
    }

    static void applySwapByValues(IntPairList ans, int[] a, int[] p, int v1, int v2) {
        int pos1 = p[v1];
        int pos2 = p[v2];

        ans.add(pos1 + 1, pos2 + 1);

        a[pos1] = v2;
        a[pos2] = v1;

        p[v1] = pos2;
        p[v2] = pos1;
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
        IntPairList ans = new IntPairList(4 * n + 10);

        int ind = p[1];

        while (ind < n - 1) {
            if (ind + half < n) {
                int target = ind + half;
                int val = a[target];
                applySwapByValues(ans, a, p, 1, val);
                ind += half;
            } else {
                int val = a[n - 1];
                applySwapByValues(ans, a, p, 1, val);
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
                    applySwapByValues(ans, a, p, v1, v2);
                    ind -= half;
                } else {
                    int v1 = a[ind];
                    int v2 = a[to];
                    applySwapByValues(ans, a, p, v1, v2);
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

                    int shiftTarget = to2 - 1;

                    if (ind != shiftTarget) {
                        int v1 = a[ind];
                        int v2 = a[shiftTarget];
                        applySwapByValues(ans, a, p, v1, v2);
                        ind = shiftTarget;
                    }

                    if (ind + half > n - j - 1) {
                        int target = n - j - 1;
                        if (ind != target) {
                            int v1 = a[ind];
                            int v2 = a[target];
                            applySwapByValues(ans, a, p, v1, v2);
                            ind = target;
                        }
                    } else {
                        int target = ind + half;
                        int v1 = a[ind];
                        int v2 = a[target];
                        applySwapByValues(ans, a, p, v1, v2);
                        ind += half;
                    }
                } else {
                    if (ind + 1 > n - j) {
                        int v1 = a[ind];
                        int v2 = a[to2];
                        applySwapByValues(ans, a, p, v1, v2);

                        int tmp = ind;
                        ind = to2;
                        to2 = tmp;

                        v1 = a[ind];
                        v2 = a[to2 - 1];
                        applySwapByValues(ans, a, p, v1, v2);

                        ind = n - j - 1;
                        j++;
                        break;
                    } else {
                        int v1 = a[ind];
                        int v2 = a[ind + 1];
                        applySwapByValues(ans, a, p, v1, v2);

                        ind++;

                        v1 = a[ind];
                        v2 = a[to2];
                        applySwapByValues(ans, a, p, v1, v2);

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

        int write = 0;
        int i = 0;
        while (i < m) {
            if (i + 1 < m && ans.x[i] == ans.y[i + 1] && ans.y[i] == ans.x[i + 1]) {
                i += 2;
            } else {
                ans.x[write] = ans.x[i];
                ans.y[write] = ans.y[i];
                write++;
                i++;
            }
        }
        ans.size = write;

        if (ans.size > 4 * n) {
            out.append(-1).append('\n');
            return;
        }

        out.append(ans.size).append('\n');
        for (i = 0; i < ans.size; i++) {
            out.append(ans.x[i]).append(' ').append(ans.y[i]).append('\n');
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