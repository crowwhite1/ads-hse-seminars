import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class C {

    static void dfs1(int v, List<List<Integer>> gr, boolean[] used, List<Integer> order) {
        used[v] = true;
        for (int to : gr.get(v)) {
            if (!used[to]) {
                dfs1(to, gr, used, order);
            }
        }
        order.add(v);
    }

    static void dfs2(int v, List<List<Integer>> rgr, boolean[] used, int[] cInd, int ind) {
        used[v] = true;
        cInd[v] = ind;
        for (int to : rgr.get(v)) {
            if (!used[to]) {
                dfs2(to, rgr, used, cInd, ind);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] first = br.readLine().split(" ");
        int n = Integer.parseInt(first[0]);
        int m = Integer.parseInt(first[1]);

        List<List<Integer>> gr = new ArrayList<>();
        List<List<Integer>> rgr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            gr.add(new ArrayList<>());
            rgr.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            String[] parts = br.readLine().split(" ");
            int u = Integer.parseInt(parts[0]) - 1;
            int v = Integer.parseInt(parts[1]) - 1;
            gr.get(u).add(v);
            rgr.get(v).add(u);
        }

        boolean[] used = new boolean[n];
        List<Integer> order = new ArrayList<>();

        for (int v = 0; v < n; v++) {
            if (!used[v]) {
                dfs1(v, gr, used, order);
            }
        }

        Collections.reverse(order);

        used = new boolean[n];
        int[] cInd = new int[n];
        for (int i = 0; i < n; i++) {
            cInd[i] = -1;
        }
        int cnt = 0;

        for (int v : order) {
            if (!used[v]) {
                cnt++;
                dfs2(v, rgr, used, cInd, cnt);
            }
        }

        StringBuilder out = new StringBuilder();
        out.append(cnt).append('\n');
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                out.append(' ');
            }
            out.append(cInd[i]);
        }
        out.append('\n');

        System.out.print(out);
    }
}
