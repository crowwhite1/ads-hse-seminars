import java.util.*;

public class dfs_stack {

    public static void dfsStack(int start, List<List<Integer>> graph, boolean[] visited) {
        ArrayDeque<Integer> st = new ArrayDeque<>();
        st.push(start);

        while (!st.isEmpty()) {
            int v = st.pop();
            if (visited[v]) continue;
            visited[v] = true;

            // Чтобы порядок был ближе к рекурсивному,
            // кладём соседей в стек в обратном порядке.
            List<Integer> adj = graph.get(v);
            for (int i = adj.size() - 1; i >= 0; i--) {
                int to = adj.get(i);
                if (!visited[to]) st.push(to);
            }
        }
    }
}