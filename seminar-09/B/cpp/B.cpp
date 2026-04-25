#include <iostream>
#include <vector>
#include <algorithm>

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

struct Edge {
    int u, v, w;
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m;
    cin >> n >> m;

    vector<Edge> edges(m);

    for (int i = 0; i < m; i++) {
        cin >> edges[i].u >> edges[i].v >> edges[i].w;
        edges[i].u--;
        edges[i].v--;
    }

    sort(edges.begin(), edges.end(), [](Edge &a, Edge &b) {
        return a.w < b.w;
    });

    DSU dsu(n);
    long long mst_weight = 0;

    for (auto &e : edges) {
        if (!dsu.is_same(e.u, e.v)) {
            dsu.unite(e.u, e.v);
            mst_weight += e.w;
        }
    }

    cout << mst_weight << '\n';

    return 0;
}