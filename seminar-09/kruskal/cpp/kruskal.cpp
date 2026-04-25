#include <algorithm>
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

    bool unite(int a, int b) {
        a = get(a);
        b = get(b);
        if (a == b) return false;

        if (sz[a] < sz[b]) swap(a, b);

        parent[b] = a;
        sz[a] += sz[b];
        return true;
    }
};

struct Edge {
    int u, v, w;
};

long long kruskal(int n, vector<Edge> &edges) {
    sort(edges.begin(), edges.end(), [](const Edge &a, const Edge &b) {
        return a.w < b.w;
    });

    DSU dsu(n);
    long long mst_weight = 0;

    for (const Edge &e : edges) {
        if (dsu.unite(e.u, e.v)) {
            mst_weight += e.w;
        }
    }

    return mst_weight;
}
