#include <iostream>
#include <vector>
#include <queue>

using namespace std;

using ll = long long;

const ll NEG_INF = -4e18;

struct Edge {
    int from;
    int to;
    int weight;
};

vector<bool> bfs(const vector<int>& starts, const vector<vector<int>>& graph) {
    int n = graph.size();

    vector<bool> used(n, false);
    queue<int> q;

    for (int start : starts) {
        used[start] = true;
        q.push(start);
    }

    while (!q.empty()) {
        int v = q.front();
        q.pop();

        for (int to : graph[v]) {
            if (!used[to]) {
                used[to] = true;
                q.push(to);
            }
        }
    }

    return used;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m;
    cin >> n >> m;

    vector<Edge> edges;
    edges.reserve(m);

    vector<vector<int>> graph(n);
    vector<vector<int>> reverseGraph(n);

    for (int i = 0; i < m; i++) {
        int a, b, w;
        cin >> a >> b >> w;

        a--;
        b--;

        edges.push_back({a, b, w});
        graph[a].push_back(b);
        reverseGraph[b].push_back(a);
    }

    vector<ll> dist(n, NEG_INF);
    dist[0] = 0;

    for (int i = 0; i < n - 1; i++) {
        bool changed = false;

        for (const Edge& edge : edges) {
            if (dist[edge.from] != NEG_INF &&
                dist[edge.to] < dist[edge.from] + edge.weight) {
                dist[edge.to] = dist[edge.from] + edge.weight;
                changed = true;
            }
        }

        if (!changed) {
            break;
        }
    }

    if (dist[n - 1] == NEG_INF) {
        cout << "Impossible\n";
        return 0;
    }

    vector<bool> reachableFromStart = bfs({0}, graph);
    vector<bool> canReachFinish = bfs({n - 1}, reverseGraph);

    for (const Edge& edge : edges) {
        if (dist[edge.from] != NEG_INF &&
            dist[edge.to] < dist[edge.from] + edge.weight) {
            if (reachableFromStart[edge.from] && canReachFinish[edge.to]) {
                cout << "Infinity\n";
                return 0;
            }
        }
    }

    cout << dist[n - 1] << "\n";

    return 0;
}