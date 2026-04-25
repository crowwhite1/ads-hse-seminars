#include <iostream>
#include <vector>
#include <set>

using namespace std;

vector<vector<int>> adj;
vector<bool> visited;
vector<int> tin, low;
set<int> cutpoints;
int timer = 0;

void dfs(int v, int parent) {
    visited[v] = true;
    tin[v] = low[v] = timer++;

    int children = 0;

    for (int to : adj[v]) {
        if (to == parent) {
            continue;
        }

        if (visited[to]) {
            low[v] = min(low[v], tin[to]);
        } else {
            dfs(to, v);
            low[v] = min(low[v], low[to]);
            children++;

            if (parent != -1 && low[to] >= tin[v]) {
                cutpoints.insert(v);
            }
        }
    }

    if (parent == -1 && children >= 2) {
        cutpoints.insert(v);
    }
}

vector<int> find_cutpoints(int n) {
    visited.assign(n, false);
    tin.assign(n, -1);
    low.assign(n, -1);
    cutpoints.clear();
    timer = 0;

    for (int v = 0; v < n; v++) {
        if (!visited[v]) {
            dfs(v, -1);
        }
    }

    return vector<int>(cutpoints.begin(), cutpoints.end());
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

    vector<int> result = find_cutpoints(n);

    cout << result.size() << '\n';
    for (int v : result) {
        cout << v + 1 << '\n';
    }

    return 0;
}