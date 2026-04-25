#include <algorithm>
#include <vector>

using namespace std;

void dfs1(int v, const vector<vector<int>> &graph, vector<bool> &used, vector<int> &order) {
    used[v] = true;
    for (int to : graph[v]) {
        if (!used[to]) {
            dfs1(to, graph, used, order);
        }
    }
    order.push_back(v);
}

void dfs2(int v, const vector<vector<int>> &reversed_graph, vector<bool> &used, vector<int> &component, int color) {
    used[v] = true;
    component[v] = color;
    for (int to : reversed_graph[v]) {
        if (!used[to]) {
            dfs2(to, reversed_graph, used, component, color);
        }
    }
}

vector<int> kosaraju(const vector<vector<int>> &graph) {
    int n = (int) graph.size();
    vector<vector<int>> reversed_graph(n);

    for (int v = 0; v < n; v++) {
        for (int to : graph[v]) {
            reversed_graph[to].push_back(v);
        }
    }

    vector<bool> used(n, false);
    vector<int> order;

    for (int v = 0; v < n; v++) {
        if (!used[v]) {
            dfs1(v, graph, used, order);
        }
    }

    reverse(order.begin(), order.end());

    fill(used.begin(), used.end(), false);
    vector<int> component(n, -1);
    int color = 0;

    for (int v : order) {
        if (!used[v]) {
            dfs2(v, reversed_graph, used, component, color);
            color++;
        }
    }

    return component;
}
