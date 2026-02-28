import java.util.*;

public class Dijkstra {

    static class Edge {
        int to;
        long weight;

        Edge(int to, long weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class State implements Comparable<State> {
        long dist;
        int node;

        State(long dist, int node) {
            this.dist = dist;
            this.node = node;
        }

        public int compareTo(State other) {
            if (this.dist != other.dist) {
                return Long.compare(this.dist, other.dist);
            }
            return Long.compare(this.node, other.node);
        }
    }

    public static long[] dijkstra(List<List<Edge>> adj, int start) {
        int n = adj.size();
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<State> pq = new PriorityQueue<>();
        pq.add(new State(0, start));

        while (!pq.isEmpty()) {
            State cur = pq.poll();

            if (cur.dist != dist[cur.node]) continue;

            for (Edge e : adj.get(cur.node)) {
                long nd = cur.dist + e.weight;
                if (nd < dist[e.to]) {
                    dist[e.to] = nd;
                    pq.add(new State(nd, e.to));
                }
            }
        }

        return dist;
    }
}