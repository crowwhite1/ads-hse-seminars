#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

class DSU {
private:
    vector<int> parent;
    vector<int> size;

public:
    DSU(int n) {
        parent.resize(n);
        size.assign(n, 1);

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    int get(int v) {
        while (parent[v] != v) {
            parent[v] = parent[parent[v]];
            v = parent[v];
        }
        return v;
    }

    bool unite(int a, int b) {
        a = get(a);
        b = get(b);

        if (a == b) {
            return false;
        }

        if (size[a] < size[b]) {
            swap(a, b);
        }

        parent[b] = a;
        size[a] += size[b];
        return true;
    }
};

struct Edge {
    int weight;
    int from;
    int to;

    bool operator<(const Edge& other) const {
        return weight < other.weight;
    }
};

int main() {
    int n;
    cin >> n;

    vector<vector<int>> weights(n, vector<int>(n));

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> weights[i][j];
        }
    }

    DSU dsu(n);
    vector<Edge> edges;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            int hasBridge;
            cin >> hasBridge;

            if (j > i) {
                if (hasBridge == 1) {
                    dsu.unite(i, j);
                } else {
                    edges.push_back({weights[i][j], i, j});
                }
            }
        }
    }

    sort(edges.begin(), edges.end());

    int answer = 0;

    for (Edge edge : edges) {
        if (dsu.unite(edge.from, edge.to)) {
            answer += edge.weight;
        }
    }

    cout << answer << "\n";

    return 0;
}