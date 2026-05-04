import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class C {
    static int n;
    static int[] a;
    static List<Integer>[] reverseGraph;

    static int[] bfs(int parity) {
        int[] dist = new int[n];
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            dist[i] = -1;
        }

        for (int i = 0; i < n; i++) {
            if (a[i] % 2 == parity) {
                dist[i] = 0;
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int v = queue.poll();

            for (int to : reverseGraph[v]) {
                if (dist[to] == -1) {
                    dist[to] = dist[v] + 1;
                    queue.add(to);
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(in.readLine());
        a = new int[n];

        String[] parts = in.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(parts[i]);
        }

        reverseGraph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            reverseGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            int left = i - a[i];
            int right = i + a[i];

            if (left >= 0) {
                reverseGraph[left].add(i);
            }

            if (right < n) {
                reverseGraph[right].add(i);
            }
        }

        int[] distEven = bfs(0);
        int[] distOdd = bfs(1);

        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < n; i++) {
            if (a[i] % 2 == 0) {
                answer.append(distOdd[i]);
            } else {
                answer.append(distEven[i]);
            }

            if (i + 1 < n) {
                answer.append(" ");
            }
        }

        System.out.println(answer);
    }
}