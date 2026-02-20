import java.util.*;

class Bfs {
    public static boolean[] bfs(int start, List<List<Integer>> graph) {
        int n = graph.size();
        boolean[] visited = new boolean[n];

        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int v = q.poll();

            for (int to : graph.get(v)) {
                if (!visited[to]) {
                    visited[to] = true;
                    q.add(to);
                }
            }
        }

        return visited;
    }
}