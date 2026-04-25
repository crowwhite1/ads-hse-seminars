import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A {
    static List<Integer>[] adj;
    static boolean[] visited;
    static int[] tin, low;
    static int timer = 0;
    static List<int[]> bridges = new ArrayList<>();

    static Map<Long, Integer> edgeId = new HashMap<>();
    static Set<Long> multiedges = new HashSet<>();

    static long key(int u, int v) {
        return (((long) u) << 32) | (v & 0xffffffffL);
    }

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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLine = br.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);

        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i <= m; i++) {
            String[] parts = br.readLine().split(" ");
            int u = Integer.parseInt(parts[0]) - 1;
            int v = Integer.parseInt(parts[1]) - 1;

            adj[u].add(v);
            adj[v].add(u);

            long uv = key(u, v);
            long vu = key(v, u);

            if (edgeId.containsKey(uv)) {
                multiedges.add(uv);
                multiedges.add(vu);
            }

            edgeId.put(uv, i);
            edgeId.put(vu, i);
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
        for (int[] bridge : bridges) {
            int u = bridge[0];
            int v = bridge[1];
            long uv = key(u, v);

            if (!multiedges.contains(uv)) {
                ans.add(edgeId.get(uv));
            }
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