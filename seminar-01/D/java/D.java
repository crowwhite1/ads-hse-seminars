import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class D {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] parts = in.readLine().trim().split(" ");
        long a = Long.parseLong(parts[0]);
        long b = Long.parseLong(parts[1]);
        long c = Long.parseLong(parts[2]);

        // Не получится решать перебором, так как в условии числа 10^15, поэтому решаем через формулу
        // (2*a + 3*b + 4*c + 5*d)/(a + b + c + d) >= 3.5
        long need = 3 * a + b - c;

        if (need <= 0) {
            System.out.println(0);
        } else {
            System.out.println((need + 2) / 3); // ceil(need / 3)
        }
    }
}
