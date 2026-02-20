import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        int[][] arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] parts = br.readLine().trim().split("\\s+");
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(parts[j]);
            }
        }

        StringBuilder out = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 1) count++;
            }

            if (count == 0) {
                out.append("0\n");
            } else {
                out.append(count);
                for (int j = 0; j < n; j++) {
                    if (arr[i][j] == 1) {
                        out.append(' ').append(j + 1); // вершины в выводе 1..n
                    }
                }
                out.append('\n');
            }
        }

        System.out.print(out.toString());
    }
}