import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class QuickSortFast {
    static final int THRESHOLD = 24;
    static final Random RNG = new Random();

    // Сортировка вставками на отрезке [l, r)
    static void insertionSort(int[] a, int l, int r) {
        for (int i = l + 1; i < r; i++) {
            int x = a[i];
            int j = i - 1;
            while (j >= l && a[j] > x) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = x;
        }
    }

    // Итеративный quick sort:
    // - pivot выбирается случайно из [l, r)
    // - 3-way partition (Dutch National Flag)
    // - стек маленький: больший кусок пушим, меньший обрабатываем сразу
    // - маленькие куски добиваем одним insertion sort'ом в конце
    static void quickSort(int[] a) {
        int n = a.length;
        if (n <= 1) return;

        int cap = 64;                // стартовая ёмкость стека
        int[] ls = new int[cap];     // левые границы
        int[] rs = new int[cap];     // правые границы
        int top = 0;

        // кладём весь массив
        ls[top] = 0;
        rs[top] = n;
        top++;

        while (top > 0) {
            top--;
            int l = ls[top];
            int r = rs[top];

            while (r - l > THRESHOLD) {
                int pivot = a[l + RNG.nextInt(r - l)]; // случайный pivot

                // 3-way partition:
                int lt = l;       // a[l:lt] < pivot
                int i = l;        // текущий индекс
                int gt = r - 1;   // a[gt+1:r] > pivot

                while (i <= gt) {
                    int ai = a[i];
                    if (ai < pivot) {
                        a[i] = a[lt];
                        a[lt] = ai;
                        lt++;
                        i++;
                    } else if (ai > pivot) {
                        a[i] = a[gt];
                        a[gt] = ai;
                        gt--;
                    } else {
                        i++;
                    }
                }

                // [l, lt) < pivot
                // [lt, gt+1) == pivot
                // [gt+1, r) > pivot

                int leftL = l, leftR = lt;
                int rightL = gt + 1, rightR = r;

                // меньший кусок обрабатываем сразу, больший — в стек
                if ((leftR - leftL) < (rightR - rightL)) {
                    if (rightR - rightL > THRESHOLD) {
                        if (top == cap) {
                            cap *= 2;
                            int[] nls = new int[cap];
                            int[] nrs = new int[cap];
                            System.arraycopy(ls, 0, nls, 0, top);
                            System.arraycopy(rs, 0, nrs, 0, top);
                            ls = nls;
                            rs = nrs;
                        }
                        ls[top] = rightL;
                        rs[top] = rightR;
                        top++;
                    }
                    l = leftL;
                    r = leftR;
                } else {
                    if (leftR - leftL > THRESHOLD) {
                        if (top == cap) {
                            cap *= 2;
                            int[] nls = new int[cap];
                            int[] nrs = new int[cap];
                            System.arraycopy(ls, 0, nls, 0, top);
                            System.arraycopy(rs, 0, nrs, 0, top);
                            ls = nls;
                            rs = nrs;
                        }
                        ls[top] = leftL;
                        rs[top] = leftR;
                        top++;
                    }
                    l = rightL;
                    r = rightR;
                }
            }
        }

        // один раз добиваем почти отсортированный массив
        insertionSort(a, 0, n);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];

        if (n > 0) {
            String[] parts = br.readLine().split(" ");
            for (int i = 0; i < n; i++) a[i] = Integer.parseInt(parts[i]);
        }

        quickSort(a);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(' ');
            sb.append(a[i]);
        }
        System.out.println(sb);
    }
}
