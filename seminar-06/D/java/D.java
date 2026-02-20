import java.io.*;
import java.util.*;

public class D {

    static List<List<Integer>> g;
    static boolean[] visited;

    static void dfs(int v) {
        visited[v] = true;
        for (int to : g.get(v)) {
            if (!visited[to]) dfs(to);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] first = br.readLine().split(" ");
        int n = Integer.parseInt(first[0]);
        int s = Integer.parseInt(first[1]) - 1; // в условии вершины с 1

        g = new ArrayList<>();
        for (int i = 0; i < n; i++) g.add(new ArrayList<>());

        for (int i = 0; i < n; i++) {
            String[] parts = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                int x = Integer.parseInt(parts[j]);
                if (x == 1) g.get(i).add(j);
            }
        }

        visited = new boolean[n];
        dfs(s);

        int cnt = 0;
        for (boolean v : visited) if (v) cnt++;

        System.out.println(cnt);
    }
}