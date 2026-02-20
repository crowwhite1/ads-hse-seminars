import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B {
    static void dfs(int v, int[][] g, boolean[] visited, int n) {
        visited[v] = true;
        for (int to = 0; to < n; to++) {
            if (g[v][to] == 1 && !visited[to]) {
                dfs(to, g, visited, n);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        int[][] g = new int[n][n];

        int edges = 0; // считаем ребра один раз (j > i)

        for (int i = 0; i < n; i++) {
            String[] parts = br.readLine().trim().split("\\s+");
            for (int j = 0; j < n; j++) {
                int x = Integer.parseInt(parts[j]);
                g[i][j] = x;
                if (x == 1 && j > i) edges++;
            }
        }

        boolean[] visited = new boolean[n];
        dfs(0, g, visited, n);

        boolean connected = true;
        for (boolean v : visited) {
            if (!v) {
                connected = false;
                break;
            }
        }

        System.out.println((edges == n - 1 && connected) ? "YES" : "NO");
    }
}