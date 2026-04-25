import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kosaraju {

    private static void dfs1(int v, List<List<Integer>> graph, boolean[] used, List<Integer> order) {
        used[v] = true;
        for (int to : graph.get(v)) {
            if (!used[to]) {
                dfs1(to, graph, used, order);
            }
        }
        order.add(v);
    }

    private static void dfs2(int v, List<List<Integer>> reversedGraph, boolean[] used, int[] component, int color) {
        used[v] = true;
        component[v] = color;
        for (int to : reversedGraph.get(v)) {
            if (!used[to]) {
                dfs2(to, reversedGraph, used, component, color);
            }
        }
    }

    public static int[] kosaraju(List<List<Integer>> graph) {
        int n = graph.size();
        List<List<Integer>> reversedGraph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            reversedGraph.add(new ArrayList<>());
        }

        for (int v = 0; v < n; v++) {
            for (int to : graph.get(v)) {
                reversedGraph.get(to).add(v);
            }
        }

        boolean[] used = new boolean[n];
        List<Integer> order = new ArrayList<>();

        for (int v = 0; v < n; v++) {
            if (!used[v]) {
                dfs1(v, graph, used, order);
            }
        }

        Collections.reverse(order);

        used = new boolean[n];
        int[] component = new int[n];
        for (int i = 0; i < n; i++) {
            component[i] = -1;
        }

        int color = 0;
        for (int v : order) {
            if (!used[v]) {
                dfs2(v, reversedGraph, used, component, color);
                color++;
            }
        }

        return component;
    }
}
