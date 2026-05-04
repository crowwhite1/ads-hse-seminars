import java.io.*;
import java.util.*;

public class D {
    static int n, m, h;
    static int[] a;
    static List<Integer>[] g, gr;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] first = in.readLine().split(" ");
        n = Integer.parseInt(first[0]);
        m = Integer.parseInt(first[1]);
        h = Integer.parseInt(first[2]);

        a = new int[n];
        String[] arr = in.readLine().split(" ");
        for (int i = 0; i < n; i++) a[i] = Integer.parseInt(arr[i]);

        g = new ArrayList[n];
        gr = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
            gr[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            String[] parts = in.readLine().split(" ");
            int u = Integer.parseInt(parts[0]) - 1;
            int v = Integer.parseInt(parts[1]) - 1;

            if ((a[u] + 1) % h == a[v]) {
                g[u].add(v);
                gr[v].add(u);
            }
            if ((a[v] + 1) % h == a[u]) {
                g[v].add(u);
                gr[u].add(v);
            }
        }

        boolean[] used = new boolean[n];
        List<Integer> order = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (used[i]) continue;

            Deque<int[]> stack = new ArrayDeque<>();
            stack.push(new int[]{i, 0});

            while (!stack.isEmpty()) {
                int[] cur = stack.pop();
                int v = cur[0], state = cur[1];

                if (state == 0) {
                    if (used[v]) continue;
                    used[v] = true;
                    stack.push(new int[]{v, 1});
                    for (int to : g[v]) {
                        if (!used[to]) stack.push(new int[]{to, 0});
                    }
                } else {
                    order.add(v);
                }
            }
        }

        Arrays.fill(used, false);
        int[] comp = new int[n];
        List<Integer> sizes = new ArrayList<>();

        for (int i = order.size() - 1; i >= 0; i--) {
            int v = order.get(i);
            if (used[v]) continue;

            Deque<Integer> stack = new ArrayDeque<>();
            stack.push(v);
            used[v] = true;

            int id = sizes.size();
            sizes.add(0);

            while (!stack.isEmpty()) {
                int cur = stack.pop();
                comp[cur] = id;
                sizes.set(id, sizes.get(id) + 1);

                for (int to : gr[cur]) {
                    if (!used[to]) {
                        used[to] = true;
                        stack.push(to);
                    }
                }
            }
        }

        int[] out = new int[sizes.size()];

        for (int v = 0; v < n; v++) {
            for (int to : g[v]) {
                if (comp[v] != comp[to]) {
                    out[comp[v]]++;
                }
            }
        }

        int best = -1;
        for (int i = 0; i < sizes.size(); i++) {
            if (out[i] == 0) {
                if (best == -1 || sizes.get(i) < sizes.get(best)) {
                    best = i;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(sizes.get(best)).append("\n");

        for (int i = 0; i < n; i++) {
            if (comp[i] == best) {
                sb.append(i + 1).append(" ");
            }
        }

        System.out.println(sb);
    }
}