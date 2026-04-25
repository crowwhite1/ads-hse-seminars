#include <iostream>
#include <vector>
#include <map>
#include <set>
#include <algorithm>

using namespace std;

vector<vector<int>> adj;
vector<bool> visited;
vector<int> tin, low;
vector<pair<int, int>> bridges;
int timer = 0;

map<pair<int, int>, int> edge_id;
set<pair<int, int>> multiedges;

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

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m;
    cin >> n >> m;

    adj.resize(n);

    for (int i = 1; i <= m; i++) {
        int u, v;
        cin >> u >> v;
        u--;
        v--;

        adj[u].push_back(v);
        adj[v].push_back(u);

        if (edge_id.count({u, v})) {
            multiedges.insert({u, v});
            multiedges.insert({v, u});
        }

        edge_id[{u, v}] = i;
        edge_id[{v, u}] = i;
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
    for (auto [u, v] : bridges) {
        if (!multiedges.count({u, v})) {
            ans.push_back(edge_id[{u, v}]);
        }
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