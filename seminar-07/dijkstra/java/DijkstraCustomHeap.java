import java.util.*;

public class DijkstraCustomHeap {

    static class MinHeap {
        static class State {
            long dist;
            int node;

            State(long d, int n) {
                dist = d;
                node = n;
            }
        }

        List<State> a = new ArrayList<>();

        boolean isEmpty() {
            return a.isEmpty();
        }

        void push(State x) {
            a.add(x);
            shiftUp(a.size() - 1);
        }

        State pop() {
            if (a.isEmpty()) return null;
            State top = a.get(0);
            State last = a.remove(a.size() - 1);
            if (!a.isEmpty()) {
                a.set(0, last);
                shiftDown(0);
            }
            return top;
        }

        void shiftUp(int i) {
            while (i > 0) {
                int p = (i - 1) / 2;
                if (a.get(p).dist <= a.get(i).dist) break;
                Collections.swap(a, p, i);
                i = p;
            }
        }

        void shiftDown(int i) {
            int n = a.size();
            while (true) {
                int l = 2 * i + 1;
                if (l >= n) break;
                int r = l + 1;
                int j = l;
                if (r < n && a.get(r).dist < a.get(l).dist) j = r;
                if (a.get(i).dist <= a.get(j).dist) break;
                Collections.swap(a, i, j);
                i = j;
            }
        }
    }

    static class Edge {
        int to;
        long weight;

        Edge(int t, long w) {
            to = t;
            weight = w;
        }
    }

    public static long[] dijkstra(List<List<Edge>> adj, int start) {
        int n = adj.size();
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[start] = 0;

        MinHeap heap = new MinHeap();
        heap.push(new MinHeap.State(0, start));

        while (!heap.isEmpty()) {
            MinHeap.State cur = heap.pop();
            if (cur.dist != dist[cur.node]) continue;

            for (Edge e : adj.get(cur.node)) {
                long nd = cur.dist + e.weight;
                if (nd < dist[e.to]) {
                    dist[e.to] = nd;
                    heap.push(new MinHeap.State(nd, e.to));
                }
            }
        }

        return dist;
    }
}