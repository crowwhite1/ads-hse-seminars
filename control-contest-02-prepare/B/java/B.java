import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class B {
    static class DSU {
        int[] parent;
        int[] size;

        DSU(int n) {
            parent = new int[n];
            size = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int get(int v) {
            while (parent[v] != v) {
                parent[v] = parent[parent[v]];
                v = parent[v];
            }
            return v;
        }

        boolean union(int a, int b) {
            a = get(a);
            b = get(b);

            if (a == b) {
                return false;
            }

            if (size[a] < size[b]) {
                int temp = a;
                a = b;
                b = temp;
            }

            parent[b] = a;
            size[a] += size[b];
            return true;
        }
    }

    static class Edge implements Comparable<Edge> {
        int weight;
        int from;
        int to;

        Edge(int weight, int from, int to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine());

        int[][] weights = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] parts = in.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                weights[i][j] = Integer.parseInt(parts[j]);
            }
        }

        DSU dsu = new DSU(n);
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] parts = in.readLine().split(" ");

            for (int j = i + 1; j < n; j++) {
                int hasBridge = Integer.parseInt(parts[j]);

                if (hasBridge == 1) {
                    dsu.union(i, j);
                } else {
                    edges.add(new Edge(weights[i][j], i, j));
                }
            }
        }

        Collections.sort(edges);

        int answer = 0;

        for (Edge edge : edges) {
            if (dsu.union(edge.from, edge.to)) {
                answer += edge.weight;
            }
        }

        System.out.println(answer);
    }
}