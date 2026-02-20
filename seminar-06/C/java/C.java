import java.io.*;
import java.util.*;

public class C {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        boolean[] isSource = new boolean[n];
        Arrays.fill(isSource, true);

        List<Integer> sinks = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] parts = br.readLine().split(" ");

            boolean hasOut = false; // есть ли исходящие рёбра

            for (int j = 0; j < n; j++) {
                int x = Integer.parseInt(parts[j]);

                if (x == 1) {
                    hasOut = true;       // из i есть ребро
                    isSource[j] = false; // в j входит ребро → не источник
                }
            }

            if (!hasOut) sinks.add(i + 1);
        }

        List<Integer> sources = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (isSource[i]) sources.add(i + 1);
        }

        StringBuilder out = new StringBuilder();

        out.append(sources.size()).append('\n');
        for (int v : sources) out.append(v).append('\n');

        out.append(sinks.size()).append('\n');
        for (int v : sinks) out.append(v).append('\n');

        System.out.print(out);
    }
}