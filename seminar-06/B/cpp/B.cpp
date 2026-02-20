#include <iostream>
#include <vector>
using namespace std;

void dfs(int v, const vector<vector<int>>& g, vector<char>& visited) {
    visited[v] = 1;
    int n = (int)g.size();
    for (int to = 0; to < n; to++) {
        if (g[v][to] == 1 && !visited[to]) {
            dfs(to, g, visited);
        }
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;

    vector<vector<int>> g(n, vector<int>(n));
    int edges = 0; // считаем каждое ребро один раз (j > i)

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> g[i][j];
            if (g[i][j] == 1 && j > i) edges++;
        }
    }

    vector<char> visited(n, 0);
    dfs(0, g, visited);

    bool connected = true;
    for (int i = 0; i < n; i++) {
        if (!visited[i]) {
            connected = false;
            break;
        }
    }

    cout << ((edges == n - 1 && connected) ? "YES" : "NO") << "\n";
    return 0;
}