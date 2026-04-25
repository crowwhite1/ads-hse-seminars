#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, w;
    cin >> n >> w;

    vector<int> p(n), c(n);
    for (int i = 0; i < n; i++) {
        cin >> p[i];
    }
    for (int i = 0; i < n; i++) {
        cin >> c[i];
    }

    vector<vector<long long>> dp(n + 1, vector<long long>(w + 1, 0));

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= w; j++) {
            if (p[i - 1] > j) {
                dp[i][j] = dp[i - 1][j];
            } else {
                dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - p[i - 1]] + c[i - 1]);
            }
        }
    }

    int i = n;
    int j = w;
    vector<int> backpack;

    while (i != 0 && j != 0) {
        if (p[i - 1] > j) {
            i--;
        } else {
            if (dp[i - 1][j] > dp[i - 1][j - p[i - 1]] + c[i - 1]) {
                i--;
            } else {
                backpack.push_back(i);
                j -= p[i - 1];
                i--;
            }
        }
    }

    long long total_cost = 0;
    long long total_weight = 0;
    for (int id : backpack) {
        total_cost += c[id - 1];
        total_weight += p[id - 1];
    }

    cout << total_cost << '\n';
    cout << total_weight << '\n';
    cout << backpack.size() << '\n';

    for (int k = 0; k < (int) backpack.size(); k++) {
        if (k > 0) cout << ' ';
        cout << p[backpack[k] - 1];
    }
    cout << '\n';

    for (int k = 0; k < (int) backpack.size(); k++) {
        if (k > 0) cout << ' ';
        cout << c[backpack[k] - 1];
    }
    cout << '\n';

    return 0;
}
