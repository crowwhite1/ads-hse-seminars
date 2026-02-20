import java.util.*;

public class dfs {

    // g: список смежности (0..n-1)
    public static void dfsRecursive(int v, List<List<Integer>> graph, boolean[] visited) {
        visited[v] = true;
        for (int to : graph.get(v)) {
            if (!visited[to]) {
                dfsRecursive(to, graph, visited);
            }
        }
    }
}