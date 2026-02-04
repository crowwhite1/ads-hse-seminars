public class BinarySearchBounds {

    // lbound: первый индекс i, такой что a[i] >= target
    // возвращает значение в диапазоне [0..n]
    public static int lbound(int[] a, int target) {
        int l = -1;
        int r = a.length;
        while (r - l > 1) {
            int m = (l + r) >>> 1; // защита от переполнения (хотя тут l=-1, r<=n)
            if (a[m] >= target) {
                r = m;
            } else {
                l = m;
            }
        }
        return r;
    }

    // rbound: последний индекс i, такой что a[i] <= target
    // возвращает значение в диапазоне [-1..n-1]
    // (эквивалент: (первый индекс > target) - 1)
    public static int rbound(int[] a, int target) {
        int l = -1;
        int r = a.length;
        while (r - l > 1) {
            int m = (l + r) >>> 1;
            if (a[m] > target) {
                r = m;
            } else {
                l = m;
            }
        }
        return l;
    }
}
