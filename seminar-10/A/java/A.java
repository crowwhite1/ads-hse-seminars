import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class A {

    static void dfs(int v, List<List<Integer>> gr, boolean[] used, List<Integer> order) {
        used[v] = true;
        for (int to : gr.get(v)) {
            if (!used[to]) {
                dfs(to, gr, used, order);
            }
        }
        order.add(v + 1);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        String[] pLine = br.readLine().split(" ");
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = Integer.parseInt(pLine[i]);
        }

        List<List<Integer>> gr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            gr.add(new ArrayList<>());
        }

        for (int fr = 0; fr < n; fr++) {
            String[] parts = br.readLine().split(" ");
            int k = Integer.parseInt(parts[0]);
            for (int j = 1; j <= k; j++) {
                gr.get(fr).add(Integer.parseInt(parts[j]) - 1);
            }
        }

        boolean[] used = new boolean[n];
        List<Integer> order = new ArrayList<>();

        dfs(0, gr, used, order);

        long ans = 0;
        for (int v : order) {
            ans += p[v - 1];
        }

        StringBuilder out = new StringBuilder();
        out.append(ans).append(' ').append(order.size()).append('\n');
        for (int i = 0; i < order.size(); i++) {
            if (i > 0) {
                out.append(' ');
            }
            out.append(order.get(i));
        }
        out.append('\n');

        System.out.print(out);
    }
}
