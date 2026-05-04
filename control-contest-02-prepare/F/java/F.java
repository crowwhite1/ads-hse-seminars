import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class F {
    static int[] bfsPath(int[][] graph, int start, int finish) {
        int n = graph.length;

        int[] dist = new int[n];
        int[] parent = new int[n];

        for (int i = 0; i < n; i++) {
            dist[i] = -1;
            parent[i] = -1;
        }

        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        dist[start] = 0;

        while (!queue.isEmpty()) {
            int v = queue.poll();

            if (v == finish) {
                break;
            }

            for (int to = 0; to < n; to++) {
                if (graph[v][to] == 1 && dist[to] == -1) {
                    dist[to] = dist[v] + 1;
                    parent[to] = v;
                    queue.add(to);
                }
            }
        }

        if (dist[finish] == -1) {
            return new int[]{-1};
        }

        int[] result = new int[dist[finish] + 2];
        result[0] = dist[finish];

        int index = result.length - 1;
        int cur = finish;

        while (cur != -1) {
            result[index] = cur + 1;
            index--;
            cur = parent[cur];
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine());

        int[][] graph = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] parts = in.readLine().split(" ");

            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(parts[j]);
            }
        }

        String[] last = in.readLine().split(" ");
        int start = Integer.parseInt(last[0]) - 1;
        int finish = Integer.parseInt(last[1]) - 1;

        int[] result = bfsPath(graph, start, finish);

        if (result[0] == -1) {
            System.out.println(-1);
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(result[0]).append("\n");

        if (result[0] > 0) {
            for (int i = 1; i < result.length; i++) {
                sb.append(result[i]);

                if (i + 1 < result.length) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}