import java.util.Collections;
import java.util.List;

public class Kruskal {

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

        boolean union(int a, int b) {
            a = get(a);
            b = get(b);
            if (a == b) return false;

            if (size[a] < size[b]) {
                int tmp = a;
                a = b;
                b = tmp;
            }

            parent[b] = a;
            size[a] += size[b];
            return true;
        }
    }

    static class Edge implements Comparable<Edge> {
        int u;
        int v;
        int w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        public int compareTo(Edge other) {
            return Integer.compare(this.w, other.w);
        }
    }

    public static long kruskal(int n, List<Edge> edges) {
        Collections.sort(edges);

        DSU dsu = new DSU(n);
        long mstWeight = 0;

        for (Edge e : edges) {
            if (dsu.union(e.u, e.v)) {
                mstWeight += e.w;
            }
        }

        return mstWeight;
    }
}
