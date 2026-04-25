import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class B {
    static List<Integer>[] adj;
    static boolean[] visited;
    static int[] tin, low;
    static int timer = 0;
    static Set<Integer> cutpoints = new HashSet<>();

    static void dfs(int v, int parent) {
        visited[v] = true;
        tin[v] = low[v] = timer++;
        int children = 0;

        for (int to : adj[v]) {
            if (to == parent) continue;

            if (visited[to]) {
                low[v] = Math.min(low[v], tin[to]);
            } else {
                dfs(to, v);
                children++;

                low[v] = Math.min(low[v], low[to]);

                if (parent != -1 && low[to] >= tin[v]) {
                    cutpoints.add(v);
                }
            }
        }

        if (parent == -1 && children >= 2) {
            cutpoints.add(v);
        }
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

        visited = new boolean[n];
        tin = new int[n];
        low = new int[n];

        Arrays.fill(tin, -1);
        Arrays.fill(low, -1);

        for (int v = 0; v < n; v++) {
            if (!visited[v]) {
                dfs(v, -1);
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int v : cutpoints) {
            ans.add(v + 1);
        }

        Collections.sort(ans);

        StringBuilder out = new StringBuilder();
        out.append(ans.size()).append('\n');
        if (!ans.isEmpty()) {
            for (int i = 0; i < ans.size(); i++) {
                if (i > 0) out.append(' ');
                out.append(ans.get(i));
            }
            out.append('\n');
        }

        System.out.print(out);
    }
}