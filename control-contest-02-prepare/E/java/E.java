import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class E {
    static final long INF = Long.MAX_VALUE / 4;

    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class Road {
        int u;
        int v;
        int w;

        Road(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    static class Node implements Comparable<Node> {
        long dist;
        int vertex;

        Node(long dist, int vertex) {
            this.dist = dist;
            this.vertex = vertex;
        }

        @Override
        public int compareTo(Node other) {
            return Long.compare(this.dist, other.dist);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] first = in.readLine().split(" ");
        int n = Integer.parseInt(first[0]);
        int m = Integer.parseInt(first[1]);
        int s = Integer.parseInt(first[2]);

        List<Edge>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        Road[] roads = new Road[m];

        for (int i = 0; i < m; i++) {
            String[] parts = in.readLine().split(" ");

            int u = Integer.parseInt(parts[0]);
            int v = Integer.parseInt(parts[1]);
            int w = Integer.parseInt(parts[2]);

            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, w));

            roads[i] = new Road(u, v, w);
        }

        int length = Integer.parseInt(in.readLine());

        long[] dist = new long[n + 1];
        Arrays.fill(dist, INF);
        dist[s] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, s));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.dist != dist[cur.vertex]) {
                continue;
            }

            for (Edge edge : graph[cur.vertex]) {
                long newDist = cur.dist + edge.weight;

                if (newDist < dist[edge.to]) {
                    dist[edge.to] = newDist;
                    pq.add(new Node(newDist, edge.to));
                }
            }
        }

        int answer = 0;

        for (int i = 1; i <= n; i++) {
            if (dist[i] == length) {
                answer++;
            }
        }

        for (Road road : roads) {
            int u = road.u;
            int v = road.v;
            int w = road.w;

            long du = dist[u];
            long dv = dist[v];

            if (du < length && dv < length) {
                long total = du + dv + w;

                if (total == 2L * length) {
                    answer++;
                } else if (total > 2L * length) {
                    answer += 2;
                }

            } else if (du < length && length < dv) {
                answer++;

            } else if (dv < length && length < du) {
                answer++;

            } else if (du == length && dv < length && dv + w > du) {
                answer++;

            } else if (dv == length && du < length && du + w > dv) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}