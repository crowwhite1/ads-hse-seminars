import java.io.*;
import java.util.*;

public class Main {

    static void testCase(BufferedReader br) throws Exception {
        String[] first = br.readLine().split(" ");
        int n = Integer.parseInt(first[0]);
        int k = Integer.parseInt(first[1]);

        int[] a = new int[n];
        String[] arr = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(arr[i]);
        }

        Arrays.sort(a);

        int mx = Math.max(Math.abs(a[0]), a[n - 1]);
        long ans = 0;

        for (int i = 0; i < n; i++) {
            if (k > 0) {
                ans += mx;
                k--;
            } else {
                ans += a[i];
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = 1;
        while (t-- > 0) {
            testCase(br);
            System.out.println();
        }
    }
}