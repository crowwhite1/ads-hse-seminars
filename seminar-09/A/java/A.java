import java.io.BufferedReader;
import java.io.InputStreamReader;

public class A {

    static class DSU {
        int[] parent;
        int[] size;

        DSU(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int get(int x) {
            if (parent[x] == x) return x;
            return parent[x] = get(parent[x]);
        }

        boolean isSame(int a, int b) {
            return get(a) == get(b);
        }

        void union(int a, int b) {
            a = get(a);
            b = get(b);
            if (a == b) return;

            if (size[a] < size[b]) {
                int tmp = a;
                a = b;
                b = tmp;
            }

            parent[b] = a;
            size[a] += size[b];
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] first = br.readLine().split(" ");
        int n = Integer.parseInt(first[0]);
        int m = Integer.parseInt(first[1]);

        DSU dsu = new DSU(n);

        int lastIdx = -1;

        for (int i = 0; i < m; i++) {
            String[] parts = br.readLine().split(" ");
            int u = Integer.parseInt(parts[0]);
            int v = Integer.parseInt(parts[1]);

            if (!dsu.isSame(u, v)) {
                dsu.union(u, v);
                lastIdx = i;
            }
        }

        System.out.println(lastIdx + 1);
    }
}