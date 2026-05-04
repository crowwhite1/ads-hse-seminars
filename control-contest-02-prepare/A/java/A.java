import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());

        for (int i = 0; i < n; i++) {
            String[] parts = in.readLine().split(" ");
            StringBuilder sb = new StringBuilder();
            int count = 0;

            for (int j = 0; j < n; j++) {
                if (parts[j].equals("1")) {
                    count++;
                    sb.append(j + 1).append(" ");
                }
            }

            System.out.print(count);
            if (count > 0) {
                System.out.print(" " + sb.toString().trim());
            }
            System.out.println();
        }
    }
}