import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class J {
    static int n, m;
    static long k;
    static int[] bankSizes;
    static int[] head;
    static int[] to;
    static int[] next;
    static int edgeCount = 0;

    static boolean canBuildRoute(int limit) {
        boolean[] active = new boolean[n];
        int activeCount = 0;

        for (int house = 0; house < n; house++) {
            if (bankSizes[house] <= limit) {
                active[house] = true;
                activeCount++;
            }
        }

        if (activeCount == 0) {
            return false;
        }

        long movesNeeded = k - 1;

        if (movesNeeded == 0) {
            return true;
        }

        int[] indegree = new int[n];

        for (int house = 0; house < n; house++) {
            if (!active[house]) {
                continue;
            }

            for (int edge = head[house]; edge != -1; edge = next[edge]) {
                int neighbour = to[edge];

                if (active[neighbour]) {
                    indegree[neighbour]++;
                }
            }
        }

        int[] queue = new int[n];
        int left = 0;
        int right = 0;

        for (int house = 0; house < n; house++) {
            if (active[house] && indegree[house] == 0) {
                queue[right++] = house;
            }
        }

        long[] longest = new long[n];
        int processed = 0;

        while (left < right) {
            int house = queue[left++];
            processed++;

            for (int edge = head[house]; edge != -1; edge = next[edge]) {
                int neighbour = to[edge];

                if (!active[neighbour]) {
                    continue;
                }

                if (longest[neighbour] < longest[house] + 1) {
                    longest[neighbour] = longest[house] + 1;

                    if (longest[neighbour] >= movesNeeded) {
                        return true;
                    }
                }

                indegree[neighbour]--;

                if (indegree[neighbour] == 0) {
                    queue[right++] = neighbour;
                }
            }
        }

        return processed < activeCount;
    }

    static void addEdge(int from, int target) {
        to[edgeCount] = target;
        next[edgeCount] = head[from];
        head[from] = edgeCount;
        edgeCount++;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] first = in.readLine().split(" ");
        n = Integer.parseInt(first[0]);
        m = Integer.parseInt(first[1]);
        k = Long.parseLong(first[2]);

        bankSizes = new int[n];

        String[] second = in.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            bankSizes[i] = Integer.parseInt(second[i]);
        }

        head = new int[n];
        Arrays.fill(head, -1);

        to = new int[m];
        next = new int[m];

        for (int i = 0; i < m; i++) {
            String[] parts = in.readLine().split(" ");

            int from = Integer.parseInt(parts[0]) - 1;
            int target = Integer.parseInt(parts[1]) - 1;

            addEdge(from, target);
        }

        int[] values = bankSizes.clone();
        Arrays.sort(values);

        int uniqueCount = 0;
        for (int value : values) {
            if (uniqueCount == 0 || values[uniqueCount - 1] != value) {
                values[uniqueCount++] = value;
            }
        }

        if (!canBuildRoute(values[uniqueCount - 1])) {
            System.out.println(-1);
            return;
        }

        int left = 0;
        int right = uniqueCount - 1;

        while (left < right) {
            int mid = (left + right) / 2;

            if (canBuildRoute(values[mid])) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(values[left]);
    }
}