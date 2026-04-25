#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    string x, y;
    cin >> x >> y;

    vector<vector<int>> dp(x.size() + 1, vector<int>(y.size() + 1, 0));

    for (int i = 0; i < (int) x.size(); i++) {
        for (int j = 0; j < (int) y.size(); j++) {
            if (x[i] == y[j]) {
                dp[i + 1][j + 1] = dp[i][j] + 1;
            } else {
                dp[i + 1][j + 1] = max(dp[i][j + 1], dp[i + 1][j]);
            }
        }
    }

    cout << dp[x.size()][y.size()] << '\n';
    return 0;
}
