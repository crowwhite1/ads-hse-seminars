import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class B {

    static class DSU {
        int[] parent, size;

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

    static class Edge implements Comparable<Edge> {
        int u, v, w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        public int compareTo(Edge other) {
            return Integer.compare(this.w, other.w);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] first = br.readLine().split(" ");
        int n = Integer.parseInt(first[0]);
        int m = Integer.parseInt(first[1]);

        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            String[] parts = br.readLine().split(" ");
            int u = Integer.parseInt(parts[0]) - 1;
            int v = Integer.parseInt(parts[1]) - 1;
            int w = Integer.parseInt(parts[2]);

            edges.add(new Edge(u, v, w));
        }

        Collections.sort(edges);

        DSU dsu = new DSU(n);
        long mstWeight = 0;

        for (Edge e : edges) {
            if (!dsu.isSame(e.u, e.v)) {
                dsu.union(e.u, e.v);
                mstWeight += e.w;
            }
        }

        System.out.println(mstWeight);
    }
}