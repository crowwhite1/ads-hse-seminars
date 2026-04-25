import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindArticulationPoints {
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
            if (to == parent) {
                continue;
            }

            if (visited[to]) {
                low[v] = Math.min(low[v], tin[to]);
            } else {
                dfs(to, v);
                low[v] = Math.min(low[v], low[to]);
                children++;

                if (parent != -1 && low[to] >= tin[v]) {
                    cutpoints.add(v);
                }
            }
        }

        if (parent == -1 && children >= 2) {
            cutpoints.add(v);
        }
    }

    static List<Integer> findCutpoints(int n) {
        visited = new boolean[n];
        tin = new int[n];
        low = new int[n];
        timer = 0;
        cutpoints = new HashSet<>();

        for (int v = 0; v < n; v++) {
            if (!visited[v]) {
                dfs(v, -1);
            }
        }

        List<Integer> result = new ArrayList<>(cutpoints);
        Collections.sort(result);
        return result;
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

        List<Integer> cutpoints = findCutpoints(n);

        StringBuilder out = new StringBuilder();
        out.append(cutpoints.size()).append('\n');
        for (int v : cutpoints) {
            out.append(v + 1).append('\n');
        }

        System.out.print(out);
    }
}