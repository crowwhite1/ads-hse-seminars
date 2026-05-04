#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m, h;
    cin >> n >> m >> h;

    vector<int> a(n);
    for (int i = 0; i < n; i++) cin >> a[i];

    vector<vector<int>> g(n), gr(n);

    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        u--; v--;

        if ((a[u] + 1) % h == a[v]) {
            g[u].push_back(v);
            gr[v].push_back(u);
        }
        if ((a[v] + 1) % h == a[u]) {
            g[v].push_back(u);
            gr[u].push_back(v);
        }
    }

    vector<bool> used(n, false);
    vector<int> order;

    for (int i = 0; i < n; i++) {
        if (used[i]) continue;

        stack<pair<int,int>> st;
        st.push({i, 0});

        while (!st.empty()) {
            auto [v, state] = st.top();
            st.pop();

            if (state == 0) {
                if (used[v]) continue;
                used[v] = true;
                st.push({v, 1});
                for (int to : g[v]) {
                    if (!used[to]) st.push({to, 0});
                }
            } else {
                order.push_back(v);
            }
        }
    }

    fill(used.begin(), used.end(), false);
    vector<int> comp(n, -1), sizes;

    for (int i = n - 1; i >= 0; i--) {
        int v = order[i];
        if (used[v]) continue;

        stack<int> st;
        st.push(v);
        used[v] = true;

        int id = sizes.size();
        sizes.push_back(0);

        while (!st.empty()) {
            int cur = st.top();
            st.pop();

            comp[cur] = id;
            sizes[id]++;

            for (int to : gr[cur]) {
                if (!used[to]) {
                    used[to] = true;
                    st.push(to);
                }
            }
        }
    }

    vector<int> out(sizes.size(), 0);

    for (int v = 0; v < n; v++) {
        for (int to : g[v]) {
            if (comp[v] != comp[to]) {
                out[comp[v]]++;
            }
        }
    }

    int best = -1;
    for (int i = 0; i < (int)sizes.size(); i++) {
        if (out[i] == 0) {
            if (best == -1 || sizes[i] < sizes[best]) {
                best = i;
            }
        }
    }

    cout << sizes[best] << "\n";
    for (int i = 0; i < n; i++) {
        if (comp[i] == best) {
            cout << i + 1 << " ";
        }
    }
    cout << "\n";
}