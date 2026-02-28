#include <bits/stdc++.h>
using namespace std;

static const long long INF = (long long)1e18;

struct Edge {
    int to;
    long long w;
};

struct Node {
    long long dist;
    int v;
};

// min-heap по (dist, v)
static inline bool lessNode(const Node& a, const Node& b) {
    if (a.dist != b.dist) return a.dist < b.dist;
    return a.v < b.v;
}

struct MinHeap {
    vector<Node> a;

    bool is_empty() const { return a.empty(); }

    void push(const Node& x) {
        a.push_back(x);
        shift_up((int)a.size() - 1);
    }

    Node pop() {
        Node top = a[0];
        Node last = a.back();
        a.pop_back();
        if (!a.empty()) {
            a[0] = last;
            shift_down(0, (int)a.size());
        }
        return top;
    }

    void shift_up(int i) {
        while (i > 0) {
            int p = (i - 1) / 2;
            if (!lessNode(a[i], a[p])) break;
            swap(a[p], a[i]);
            i = p;
        }
    }

    void shift_down(int i, int n) {
        while (true) {
            int l = 2 * i + 1;
            if (l >= n) break;
            int r = l + 1;
            int j = l;
            if (r < n && lessNode(a[r], a[l])) j = r;
            if (!lessNode(a[j], a[i])) break;
            swap(a[i], a[j]);
            i = j;
        }
    }
};

vector<long long> dijkstra(const vector<vector<Edge>>& adj, int start) {
    int n = (int)adj.size();
    vector<long long> dist(n, INF);
    dist[start] = 0;

    MinHeap q;
    q.push({0, start});

    while (!q.is_empty()) {
        Node cur = q.pop();
        long long curDist = cur.dist;
        int v = cur.v;

        if (curDist != dist[v]) continue;

        for (const auto& e : adj[v]) {
            long long nd = curDist + e.w;
            if (nd < dist[e.to]) {
                dist[e.to] = nd;
                q.push({nd, e.to});
            }
        }
    }

    return dist;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m;
    cin >> n >> m;
    vector<vector<Edge>> adj(n);
    for (int i = 0; i < m; i++) {
        int fr, to;
        long long w;
        cin >> fr >> to >> w;
        adj[fr].push_back({to, w});
    }

    auto dist = dijkstra(adj, 0);
    for (int i = 1; i < n; i++) {
        cout << dist[i] << "\n";
    }
    return 0;
}