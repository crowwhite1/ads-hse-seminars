import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class G {
    static final long NEG_INF = Long.MIN_VALUE / 4;

    static class Edge {
        int from;
        int to;
        int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    static boolean[] bfs(List<Integer> starts, List<Integer>[] graph) {
        int n = graph.length;
        boolean[] used = new boolean[n];
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        for (int start : starts) {
            used[start] = true;
            queue.add(start);
        }

        while (!queue.isEmpty()) {
            int v = queue.poll();

            for (int to : graph[v]) {
                if (!used[to]) {
                    used[to] = true;
                    queue.add(to);
                }
            }
        }

        return used;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] first = in.readLine().split(" ");
        int n = Integer.parseInt(first[0]);
        int m = Integer.parseInt(first[1]);

        Edge[] edges = new Edge[m];

        List<Integer>[] graph = new ArrayList[n];
        List<Integer>[] reverseGraph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            String[] parts = in.readLine().split(" ");

            int a = Integer.parseInt(parts[0]) - 1;
            int b = Integer.parseInt(parts[1]) - 1;
            int w = Integer.parseInt(parts[2]);

            edges[i] = new Edge(a, b, w);
            graph[a].add(b);
            reverseGraph[b].add(a);
        }

        long[] dist = new long[n];
        Arrays.fill(dist, NEG_INF);
        dist[0] = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean changed = false;

            for (Edge edge : edges) {
                if (dist[edge.from] != NEG_INF &&
                        dist[edge.to] < dist[edge.from] + edge.weight) {
                    dist[edge.to] = dist[edge.from] + edge.weight;
                    changed = true;
                }
            }

            if (!changed) {
                break;
            }
        }

        if (dist[n - 1] == NEG_INF) {
            System.out.println("Impossible");
            return;
        }

        boolean[] reachableFromStart = bfs(List.of(0), graph);
        boolean[] canReachFinish = bfs(List.of(n - 1), reverseGraph);

        for (Edge edge : edges) {
            if (dist[edge.from] != NEG_INF &&
                    dist[edge.to] < dist[edge.from] + edge.weight) {
                if (reachableFromStart[edge.from] && canReachFinish[edge.to]) {
                    System.out.println("Infinity");
                    return;
                }
            }
        }

        System.out.println(dist[n - 1]);
    }
}