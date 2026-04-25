#include <iostream>
#include <vector>

using namespace std;

vector<vector<int>> adj;
vector<bool> visited;
vector<int> tin, low;
vector<pair<int, int>> bridges;
int timer = 0;

void dfs(int v, int parent) {
    visited[v] = true;
    tin[v] = low[v] = timer++;

    for (int to : adj[v]) {
        if (to == parent) {
            continue;
        }

        if (visited[to]) {
            low[v] = min(low[v], tin[to]);
        } else {
            dfs(to, v);
            low[v] = min(low[v], low[to]);

            if (low[to] > tin[v]) {
                bridges.push_back({v, to});
            }
        }
    }
}

vector<pair<int, int>> find_bridges(int n) {
    visited.assign(n, false);
    tin.assign(n, -1);
    low.assign(n, -1);
    bridges.clear();
    timer = 0;

    for (int v = 0; v < n; v++) {
        if (!visited[v]) {
            dfs(v, -1);
        }
    }

    return bridges;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m;
    cin >> n >> m;

    adj.assign(n, {});

    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        u--;
        v--;

        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    vector<pair<int, int>> result = find_bridges(n);

    cout << result.size() << '\n';
    for (auto [u, v] : result) {
        cout << u + 1 << ' ' << v + 1 << '\n';
    }

    return 0;
}