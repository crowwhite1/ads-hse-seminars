#include <vector>
#include <queue>
using namespace std;

vector<bool> bfs(int start, const vector<vector<int>>& graph) {
    int n = graph.size();
    vector<bool> visited(n, false);

    queue<int> q;
    q.push(start);
    visited[start] = true;

    while (!q.empty()) {
        int v = q.front();
        q.pop();

        for (int to : graph[v]) {
            if (!visited[to]) {
                visited[to] = true;
                q.push(to);
            }
        }
    }

    return visited;
}