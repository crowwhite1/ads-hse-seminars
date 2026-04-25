#include <iostream>
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

    void unite(int a, int b) {
        a = get(a);
        b = get(b);
        if (a == b) return;

        if (sz[a] < sz[b]) swap(a, b);

        parent[b] = a;
        sz[a] += sz[b];
    }
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m;
    cin >> n >> m;

    DSU dsu(n);

    int last_idx = -1;

    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;

        if (!dsu.is_same(u, v)) {
            dsu.unite(u, v);
            last_idx = i;
        }
    }

    cout << last_idx + 1 << '\n';

    return 0;
}