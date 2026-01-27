import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class D {

    static List<Integer> quickSort(List<Integer> a) {
        if (a.size() <= 1) {
            return a;
        }

        int pivot = a.get(a.size() / 2);
        List<Integer> left = new ArrayList<>();
        List<Integer> mid = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int x : a) {
            if (x < pivot) {
                left.add(x);
            } else if (x == pivot) {
                mid.add(x);
            } else {
                right.add(x);
            }
        }

        List<Integer> res = new ArrayList<>();
        res.addAll(quickSort(left));
        res.addAll(mid);
        res.addAll(quickSort(right));
        return res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        List<Integer> a = new ArrayList<>();

        if (n > 0) {
            String[] parts = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                a.add(Integer.parseInt(parts[i]));
            }
        }

        a = quickSort(a);

        int unique = 1;
        for (int i = 1; i < n; i++) {
            if (!a.get(i).equals(a.get(i - 1))) {
                unique++;
            }
        }

        System.out.println(unique);
    }
}
