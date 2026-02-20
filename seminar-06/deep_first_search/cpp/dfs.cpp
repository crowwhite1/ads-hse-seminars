#include <vector>
using namespace std;

void dfs_recursive(int v, const vector<vector<int>>& g, vector<char>& visited) {
    visited[v] = 1;
    for (int to : g[v]) {
        if (!visited[to]) dfs_recursive(to, g, visited);
    }
}