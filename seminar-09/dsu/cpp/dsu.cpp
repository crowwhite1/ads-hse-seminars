#include <vector>

using namespace std;

struct DSU {
    vector<int> parent, sz;

    DSU(int n) {
        parent.resize(n);
        sz.assign(n, 1);
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    int get(int x) {
        if (parent[x] == x) return x;
        return parent[x] = get(parent[x]);
    }

    bool is_same(int a, int b) {
        return get(a) == get(b);
    }

    bool unite(int a, int b) {
        a = get(a);
        b = get(b);
        if (a == b) return false;

        if (sz[a] < sz[b]) swap(a, b);

        parent[b] = a;
        sz[a] += sz[b];
        return true;
    }

    int size(int x) {
        return sz[get(x)];
    }
};
