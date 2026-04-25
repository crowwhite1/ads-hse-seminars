import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class B {

    static void dfs(int v, List<List<Integer>> gr, int[] color, List<Integer> order, boolean[] hasCycle) {
        color[v] = 1;

        for (int to : gr.get(v)) {
            if (color[to] == 0) {
                dfs(to, gr, color, order, hasCycle);
            } else if (color[to] == 1) {
                hasCycle[0] = true;
            }
        }

        color[v] = 2;
        order.add(v + 1);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] first = br.readLine().split(" ");
        int n = Integer.parseInt(first[0]);
        int m = Integer.parseInt(first[1]);

        List<List<Integer>> gr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            gr.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            String[] parts = br.readLine().split(" ");
            int fr = Integer.parseInt(parts[0]) - 1;
            int to = Integer.parseInt(parts[1]) - 1;
            gr.get(fr).add(to);
        }

        int[] color = new int[n];
        List<Integer> order = new ArrayList<>();
        boolean[] hasCycle = new boolean[1];

        for (int v = 0; v < n; v++) {
            if (color[v] == 0) {
                dfs(v, gr, color, order, hasCycle);
            }
        }

        Collections.reverse(order);

        StringBuilder out = new StringBuilder();
        if (hasCycle[0]) {
            out.append("No\n");
        } else {
            out.append("Yes\n");
            for (int i = 0; i < order.size(); i++) {
                if (i > 0) {
                    out.append(' ');
                }
                out.append(order.get(i));
            }
            out.append('\n');
        }

        System.out.print(out);
    }
}
