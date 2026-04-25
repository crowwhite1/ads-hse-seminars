#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;

void dfs1(int v, const vector<vector<int>> &gr, vector<bool> &used, vector<int> &order) {
    used[v] = true;
    for (int to : gr[v]) {
        if (!used[to]) {
            dfs1(to, gr, used, order);
        }
    }
    order.push_back(v);
}

void dfs2(int v, const vector<vector<int>> &rgr, vector<bool> &used, vector<int> &c_ind, int ind) {
    used[v] = true;
    c_ind[v] = ind;
    for (int to : rgr[v]) {
        if (!used[to]) {
            dfs2(to, rgr, used, c_ind, ind);
        }
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m;
    cin >> n >> m;

    vector<vector<int>> gr(n), rgr(n);

    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        u--;
        v--;
        gr[u].push_back(v);
        rgr[v].push_back(u);
    }

    vector<bool> used(n, false);
    vector<int> order;

    for (int v = 0; v < n; v++) {
        if (!used[v]) {
            dfs1(v, gr, used, order);
        }
    }

    reverse(order.begin(), order.end());

    fill(used.begin(), used.end(), false);
    vector<int> c_ind(n, -1);
    int cnt = 0;

    for (int v : order) {
        if (!used[v]) {
            cnt++;
            dfs2(v, rgr, used, c_ind, cnt);
        }
    }

    cout << cnt << '\n';
    for (int i = 0; i < n; i++) {
        if (i > 0) cout << ' ';
        cout << c_ind[i];
    }
    cout << '\n';

    return 0;
}
