#include <algorithm>
#include <vector>

using namespace std;

bool dfs(int v, const vector<vector<int>> &graph, vector<int> &color, vector<int> &order) {
    color[v] = 1;

    for (int to : graph[v]) {
        if (color[to] == 0) {
            if (!dfs(to, graph, color, order)) {
                return false;
            }
        } else if (color[to] == 1) {
            return false;
        }
    }

    color[v] = 2;
    order.push_back(v);
    return true;
}

vector<int> topological_sort(const vector<vector<int>> &graph) {
    int n = (int) graph.size();
    vector<int> color(n, 0);
    vector<int> order;

    for (int v = 0; v < n; v++) {
        if (color[v] == 0) {
            if (!dfs(v, graph, color, order)) {
                return {};
            }
        }
    }

    reverse(order.begin(), order.end());
    return order;
}
