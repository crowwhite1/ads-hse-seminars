#include <iostream>
#include <vector>
using namespace std;

vector<vector<int>> g;
vector<char> visited;

void dfs(int v) {
    visited[v] = 1;
    for (int to : g[v]) {
        if (!visited[to]) dfs(to);
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, s;
    cin >> n >> s;
    s--; // 1-indexed -> 0-indexed

    g.assign(n, {});
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            int x;
            cin >> x;
            if (x == 1) g[i].push_back(j);
        }
    }

    visited.assign(n, 0);
    dfs(s);

    int cnt = 0;
    for (int i = 0; i < n; i++) if (visited[i]) cnt++;

    cout << cnt << "\n";
    return 0;
}