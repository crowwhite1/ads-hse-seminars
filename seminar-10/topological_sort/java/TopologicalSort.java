import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TopologicalSort {

    private static boolean dfs(int v, List<List<Integer>> graph, int[] color, List<Integer> order) {
        color[v] = 1;

        for (int to : graph.get(v)) {
            if (color[to] == 0) {
                if (!dfs(to, graph, color, order)) {
                    return false;
                }
            } else if (color[to] == 1) {
                return false;
            }
        }

        color[v] = 2;
        order.add(v);
        return true;
    }

    public static List<Integer> topologicalSort(List<List<Integer>> graph) {
        int n = graph.size();
        int[] color = new int[n];
        List<Integer> order = new ArrayList<>();

        for (int v = 0; v < n; v++) {
            if (color[v] == 0) {
                if (!dfs(v, graph, color, order)) {
                    return new ArrayList<>();
                }
            }
        }

        Collections.reverse(order);
        return order;
    }
}
