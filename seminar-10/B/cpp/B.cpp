#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;

void dfs(int v, const vector<vector<int>> &gr, vector<int> &color, vector<int> &order, bool &has_cycle) {
    color[v] = 1;

    for (int to : gr[v]) {
        if (color[to] == 0) {
            dfs(to, gr, color, order, has_cycle);
        } else if (color[to] == 1) {
            has_cycle = true;
        }
    }

    color[v] = 2;
    order.push_back(v + 1);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m;
    cin >> n >> m;

    vector<vector<int>> gr(n);

    for (int i = 0; i < m; i++) {
        int fr, to;
        cin >> fr >> to;
        gr[fr - 1].push_back(to - 1);
    }

    vector<int> color(n, 0);
    vector<int> order;
    bool has_cycle = false;

    for (int v = 0; v < n; v++) {
        if (color[v] == 0) {
            dfs(v, gr, color, order, has_cycle);
        }
    }

    reverse(order.begin(), order.end());

    if (has_cycle) {
        cout << "No\n";
    } else {
        cout << "Yes\n";
        for (int i = 0; i < (int) order.size(); i++) {
            if (i > 0) cout << ' ';
            cout << order[i];
        }
        cout << '\n';
    }

    return 0;
}
