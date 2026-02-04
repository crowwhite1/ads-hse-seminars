import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C {

    static boolean canFit(long m, int w, int h, int n) {
        long a = m / w; // сколько влезает по ширине
        long b = m / h; // сколько влезает по высоте
        if (a == 0 || b == 0) return false;
        return a*b >= n;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] parts = br.readLine().trim().split("\\s+");
        int w = Integer.parseInt(parts[0]);
        int h = Integer.parseInt(parts[1]);
        int n = Integer.parseInt(parts[2]);

        long l = 0;
        long r = 100_000L * Math.max(w, h); // безопасная верхняя граница (см. README)

        while (r - l > 1) {
            long m = l + (r - l) / 2;
            if (canFit(m, w, h, n)) r = m;
            else l = m;
        }

        System.out.println(r);
    }
}
