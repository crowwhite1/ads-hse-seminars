#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

pair<int, vector<int>> bfsPath(
    const vector<vector<int>>& graph,
    int start,
    int finish
) {
    int n = graph.size();

    vector<int> dist(n, -1);
    vector<int> parent(n, -1);

    queue<int> q;
    q.push(start);
    dist[start] = 0;

    while (!q.empty()) {
        int v = q.front();
        q.pop();

        if (v == finish) {
            break;
        }

        for (int to = 0; to < n; to++) {
            if (graph[v][to] == 1 && dist[to] == -1) {
                dist[to] = dist[v] + 1;
                parent[to] = v;
                q.push(to);
            }
        }
    }

    if (dist[finish] == -1) {
        return {-1, {}};
    }

    vector<int> path;

    for (int cur = finish; cur != -1; cur = parent[cur]) {
        path.push_back(cur + 1);
    }

    reverse(path.begin(), path.end());

    return {dist[finish], path};
}

int main() {
    int n;
    cin >> n;

    vector<vector<int>> graph(n, vector<int>(n));

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> graph[i][j];
        }
    }

    int start, finish;
    cin >> start >> finish;

    start--;
    finish--;

    auto [distance, path] = bfsPath(graph, start, finish);

    if (distance == -1) {
        cout << -1 << "\n";
        return 0;
    }

    cout << distance << "\n";

    if (distance > 0) {
        for (int i = 0; i < (int)path.size(); i++) {
            cout << path[i];

            if (i + 1 < (int)path.size()) {
                cout << " ";
            }
        }
        cout << "\n";
    }

    return 0;
}