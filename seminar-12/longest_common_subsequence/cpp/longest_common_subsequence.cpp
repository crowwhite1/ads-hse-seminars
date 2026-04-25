#include <vector>
#include <string>
#include <algorithm>

using namespace std;

int longest_common_subsequence(const string &x, const string &y) {
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

    return dp[x.size()][y.size()];
}
