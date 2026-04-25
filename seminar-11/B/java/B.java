import java.io.*;
import java.util.*;

public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] firstLine = br.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);
        
        int[][] c = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                c[i][j] = Integer.parseInt(line[j]);
            }
        }
        
        int[][] a = new int[n][m];
        a[0][0] = c[0][0];
        
        for (int i = 1; i < n; i++) {
            a[i][0] = a[i - 1][0] + c[i][0];
        }
        
        for (int j = 1; j < m; j++) {
            a[0][j] = a[0][j - 1] + c[0][j];
        }
        
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                a[i][j] = Math.max(a[i - 1][j], a[i][j - 1]) + c[i][j];
            }
        }
        
        System.out.println(a[n - 1][m - 1]);
        
        int i = n - 1;
        int j = m - 1;
        List<String> path = new ArrayList<>();
        
        while (!(i == 0 && j == 0)) {
            if (i == 0) {
                path.add("R");
                j -= 1;
            } else if (j == 0) {
                path.add("D");
                i -= 1;
            } else {
                if (a[i - 1][j] > a[i][j - 1]) {
                    path.add("D");
                    i -= 1;
                } else {
                    path.add("R");
                    j -= 1;
                }
            }
        }
        
        Collections.reverse(path);
        System.out.println(String.join(" ", path));
    }
}