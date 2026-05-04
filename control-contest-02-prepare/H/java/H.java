import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class H {
    static final long INF = Long.MAX_VALUE / 4;

    static class Road {
        int to;
        int time;

        Road(int to, int time) {
            this.to = to;
            this.time = time;
        }
    }

    static class State implements Comparable<State> {
        long time;
        int playground;
        int usedMismatch;

        State(long time, int playground, int usedMismatch) {
            this.time = time;
            this.playground = playground;
            this.usedMismatch = usedMismatch;
        }

        @Override
        public int compareTo(State other) {
            return Long.compare(this.time, other.time);
        }
    }

    static long[][] dijkstra(int n, ArrayList<Road>[] graph, int start, boolean[] hasMismatch) {
        long[][] dist = new long[n + 1][2];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
        }

        int startState = hasMismatch[start] ? 1 : 0;
        dist[start][startState] = 0;

        PriorityQueue<State> queue = new PriorityQueue<>();
        queue.add(new State(0, start, startState));

        while (!queue.isEmpty()) {
            State current = queue.poll();

            if (current.time != dist[current.playground][current.usedMismatch]) {
                continue;
            }

            for (Road road : graph[current.playground]) {
                int nextUsedMismatch = (current.usedMismatch == 1 || hasMismatch[road.to]) ? 1 : 0;
                long newTime = current.time + (current.usedMismatch == 1 ? road.time / 2 : road.time);

                if (newTime < dist[road.to][nextUsedMismatch]) {
                    dist[road.to][nextUsedMismatch] = newTime;
                    queue.add(new State(newTime, road.to, nextUsedMismatch));
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int tests = Integer.parseInt(in.readLine());
        StringBuilder output = new StringBuilder();

        for (int test = 0; test < tests; test++) {
            String[] first = in.readLine().split(" ");

            int n = Integer.parseInt(first[0]);
            int m = Integer.parseInt(first[1]);
            int k = Integer.parseInt(first[2]);

            boolean[] hasMismatch = new boolean[n + 1];

            String[] mismatchPlaygrounds = in.readLine().split(" ");
            for (int i = 0; i < k; i++) {
                int playground = Integer.parseInt(mismatchPlaygrounds[i]);
                hasMismatch[playground] = true;
            }

            ArrayList<Road>[] graph = new ArrayList[n + 1];

            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                String[] parts = in.readLine().split(" ");

                int firstPlayground = Integer.parseInt(parts[0]);
                int secondPlayground = Integer.parseInt(parts[1]);
                int time = Integer.parseInt(parts[2]);

                graph[firstPlayground].add(new Road(secondPlayground, time));
                graph[secondPlayground].add(new Road(firstPlayground, time));
            }

            long[][] madinDist = dijkstra(n, graph, 1, hasMismatch);
            long[][] goshaDist = dijkstra(n, graph, n, hasMismatch);

            long answer = INF;

            for (int playground = 1; playground <= n; playground++) {
                long madinTime = Math.min(madinDist[playground][0], madinDist[playground][1]);
                long goshaTime = Math.min(goshaDist[playground][0], goshaDist[playground][1]);

                answer = Math.min(answer, Math.max(madinTime, goshaTime));
            }

            output.append(answer == INF ? -1 : answer).append('\n');
        }

        System.out.print(output);
    }
}