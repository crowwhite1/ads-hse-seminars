import java.io.*;
import java.util.*;

public class C {
    static final long INF = (long) 1e18;

    static class Edge {
        int to;
        long w;
        Edge(int to, long w) {
            this.to = to;
            this.w = w;
        }
    }

    // Пара (dist, v) + сравнение по dist, потом по v
    static class Node implements Comparable<Node> {
        long dist;
        int v;
        Node(long dist, int v) {
            this.dist = dist;
            this.v = v;
        }
        @Override
        public int compareTo(Node o) {
            if (this.dist < o.dist) return -1;
            if (this.dist > o.dist) return 1;
            return Integer.compare(this.v, o.v);
        }
    }

    static class MinHeap {
        private final ArrayList<Node> a = new ArrayList<>();

        boolean isEmpty() {
            return a.isEmpty();
        }

        void push(Node x) {
            a.add(x);
            shiftUp(a.size() - 1);
        }

        Node pop() {
            Node top = a.get(0);
            Node last = a.remove(a.size() - 1);
            if (!a.isEmpty()) {
                a.set(0, last);
                shiftDown(0, a.size());
            }
            return top;
        }

        private void shiftUp(int i) {
            while (i > 0) {
                int p = (i - 1) / 2;
                if (a.get(p).compareTo(a.get(i)) <= 0) break;
                Node tmp = a.get(p);
                a.set(p, a.get(i));
                a.set(i, tmp);
                i = p;
            }
        }

        private void shiftDown(int i, int n) {
            while (true) {
                int l = 2 * i + 1;
                if (l >= n) break;
                int r = l + 1;
                int j = l;
                if (r < n && a.get(r).compareTo(a.get(l)) < 0) j = r;
                if (a.get(i).compareTo(a.get(j)) <= 0) break;
                Node tmp = a.get(i);
                a.set(i, a.get(j));
                a.set(j, tmp);
                i = j;
            }
        }
    }

    static long[] dijkstra(ArrayList<Edge>[] adj, int start) {
        int n = adj.length;
        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        MinHeap q = new MinHeap();
        q.push(new Node(0, start));

        while (!q.isEmpty()) {
            Node cur = q.pop();
            long curDist = cur.dist;
            int v = cur.v;

            if (curDist != dist[v]) continue;

            for (Edge e : adj[v]) {
                int to = e.to;
                long nd = curDist + e.w;
                if (nd < dist[to]) {
                    dist[to] = nd;
                    q.push(new Node(nd, to));
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] first = in.readLine().split(" ");
        int n = Integer.parseInt(first[0]);
        int m = Integer.parseInt(first[1]);

        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            String[] parts = in.readLine().split(" ");
            int fr = Integer.parseInt(parts[0]);
            int to = Integer.parseInt(parts[1]);
            long w = Long.parseLong(parts[2]);
            adj[fr].add(new Edge(to, w));
        }

        long[] dist = dijkstra(adj, 0);

        StringBuilder out = new StringBuilder();
        for (int i = 1; i < n; i++) {
            out.append(dist[i]).append('\n');
        }
        System.out.print(out.toString());
    }
}