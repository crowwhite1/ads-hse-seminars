import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FindBridges {
    static List<Integer>[] adj;
    static boolean[] visited;
    static int[] tin, low;
    static int timer = 0;
    static List<int[]> bridges = new ArrayList<>();

    static void dfs(int v, int parent) {
        visited[v] = true;
        tin[v] = low[v] = timer++;

        for (int to : adj[v]) {
            if (to == parent) {
                continue;
            }

            if (visited[to]) {
                low[v] = Math.min(low[v], tin[to]);
            } else {
                dfs(to, v);
                low[v] = Math.min(low[v], low[to]);

                if (low[to] > tin[v]) {
                    bridges.add(new int[]{v, to});
                }
            }
        }
    }

    static List<int[]> findBridges(int n) {
        visited = new boolean[n];
        tin = new int[n];
        low = new int[n];
        bridges = new ArrayList<>();
        timer = 0;

        for (int v = 0; v < n; v++) {
            if (!visited[v]) {
                dfs(v, -1);
            }
        }

        return bridges;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] first = br.readLine().split(" ");
        int n = Integer.parseInt(first[0]);
        int m = Integer.parseInt(first[1]);

        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            String[] parts = br.readLine().split(" ");
            int u = Integer.parseInt(parts[0]) - 1;
            int v = Integer.parseInt(parts[1]) - 1;

            adj[u].add(v);
            adj[v].add(u);
        }

        List<int[]> bridges = findBridges(n);

        StringBuilder out = new StringBuilder();
        out.append(bridges.size()).append('\n');
        for (int[] edge : bridges) {
            out.append(edge[0] + 1).append(' ').append(edge[1] + 1).append('\n');
        }

        System.out.print(out);
    }
}