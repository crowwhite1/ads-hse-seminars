import java.io.BufferedReader;
import java.io.InputStreamReader;

public class QuickSortFast {

    // Порог, ниже которого не продолжаем quick sort,
    // а потом добиваем всё insertion sort'ом
    static final int THRESHOLD = 24;

    // Сортировка вставками на отрезке [l, r)
    // Быстра на маленьких массивах
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

    // Возвращает медиану из трёх значений
    // Используется для более “качественного” pivot
    static int medianOfThree(int x, int y, int z) {
        if (x < y) {
            if (y < z) return y;
            return (x < z) ? z : x;
        } else {
            if (x < z) return x;
            return (y < z) ? z : y;
        }
    }

    static void quickSort(int[] a) {
        int n = a.length;
        if (n <= 1) return;

        // Стек отрезков [l, r), которые нужно обработать
        int cap = 64;                 // начальная ёмкость стека
        int[] ls = new int[cap];      // левые границы
        int[] rs = new int[cap];      // правые границы
        int top = 0;

        // Кладём весь массив в стек
        ls[top] = 0;
        rs[top] = n;
        top++;

        // Пока есть отрезки для обработки
        while (top > 0) {
            // Берём очередной отрезок
            top--;
            int l = ls[top];
            int r = rs[top];

            // Пока отрезок “достаточно большой” — режем quick sort'ом
            while (r - l > THRESHOLD) {

                // Выбор pivot: медиана из a[l], a[mid], a[r-1]
                int m = (l + r) >>> 1;
                int pivot = medianOfThree(a[l], a[m], a[r - 1]);

                // 3-way partition (Dutch National Flag)
                int lt = l;        // a[l:lt] < pivot
                int i = l;         // текущий индекс
                int gt = r - 1;    // a[gt+1:r] > pivot

                while (i <= gt) {
                    int ai = a[i];
                    if (ai < pivot) {
                        // переносим элемент в зону < pivot
                        a[i] = a[lt];
                        a[lt] = ai;
                        lt++;
                        i++;
                    } else if (ai > pivot) {
                        // переносим элемент в зону > pivot
                        a[i] = a[gt];
                        a[gt] = ai;
                        gt--;
                    } else {
                        // элемент == pivot
                        i++;
                    }
                }

                // Теперь:
                // [l, lt)      < pivot
                // [lt, gt+1)   == pivot
                // [gt+1, r)    > pivot

                int leftL = l, leftR = lt;
                int rightL = gt + 1, rightR = r;

                // Чтобы стек был маленьким:
                // больший кусок кладём в стек,
                // меньший обрабатываем сразу
                if ((leftR - leftL) < (rightR - rightL)) {
                    if (rightR - rightL > THRESHOLD) {
                        // расширяем стек при необходимости
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

        // Один раз добиваем весь массив insertion sort'ом
        insertionSort(a, 0, n);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];

        if (n > 0) {
            String[] parts = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(parts[i]);
            }
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
