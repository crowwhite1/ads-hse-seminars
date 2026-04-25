#include <iostream>
#include <vector>

using namespace std;

void dfs(int v, const vector<vector<int>> &gr, vector<bool> &used, vector<int> &order) {
    used[v] = true;
    for (int to : gr[v]) {
        if (!used[to]) {
            dfs(to, gr, used, order);
        }
    }
    order.push_back(v + 1);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;

    vector<int> p(n);
    for (int i = 0; i < n; i++) {
        cin >> p[i];
    }

    vector<vector<int>> gr(n);

    for (int fr = 0; fr < n; fr++) {
        int k;
        cin >> k;
        for (int j = 0; j < k; j++) {
            int to;
            cin >> to;
            gr[fr].push_back(to - 1);
        }
    }

    vector<bool> used(n, false);
    vector<int> order;

    dfs(0, gr, used, order);

    long long ans = 0;
    for (int v : order) {
        ans += p[v - 1];
    }

    cout << ans << ' ' << order.size() << '\n';
    for (int i = 0; i < (int) order.size(); i++) {
        if (i > 0) cout << ' ';
        cout << order[i];
    }
    cout << '\n';

    return 0;
}
