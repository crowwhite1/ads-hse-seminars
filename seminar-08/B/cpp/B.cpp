#include <iostream>
#include <vector>
#include <set>
#include <algorithm>

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
        if (to == parent) continue;

        if (visited[to]) {
            low[v] = min(low[v], tin[to]);
        } else {
            dfs(to, v);
            children++;

            low[v] = min(low[v], low[to]);

            if (parent != -1 && low[to] >= tin[v]) {
                cutpoints.insert(v);
            }
        }
    }

    if (parent == -1 && children >= 2) {
        cutpoints.insert(v);
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m;
    cin >> n >> m;

    adj.resize(n);

    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        u--;
        v--;

        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    visited.assign(n, false);
    tin.assign(n, -1);
    low.assign(n, -1);

    for (int v = 0; v < n; v++) {
        if (!visited[v]) {
            dfs(v, -1);
        }
    }

    vector<int> ans;
    for (int v : cutpoints) {
        ans.push_back(v + 1);
    }

    sort(ans.begin(), ans.end());

    cout << ans.size() << '\n';
    if (!ans.empty()) {
        for (int i = 0; i < (int)ans.size(); i++) {
            if (i > 0) cout << ' ';
            cout << ans[i];
        }
        cout << '\n';
    }

    return 0;
}