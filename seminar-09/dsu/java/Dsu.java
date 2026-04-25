public class Dsu {

    int[] parent;
    int[] size;

    public Dsu(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int get(int x) {
        if (parent[x] == x) return x;
        return parent[x] = get(parent[x]);
    }

    public boolean isSame(int a, int b) {
        return get(a) == get(b);
    }

    public boolean union(int a, int b) {
        a = get(a);
        b = get(b);
        if (a == b) return false;

        if (size[a] < size[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        parent[b] = a;
        size[a] += size[b];
        return true;
    }

    public int size(int x) {
        return size[get(x)];
    }
}
