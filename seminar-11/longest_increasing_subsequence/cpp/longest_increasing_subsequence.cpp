#include <vector>

using namespace std;

vector<int> longest_increasing_subsequence(const vector<int> &a) {
    int n = (int) a.size();
    vector<int> dp(n, 1);
    vector<int> prev(n, -1);

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < i; j++) {
            if (a[j] < a[i] && dp[j] + 1 > dp[i]) {
                dp[i] = dp[j] + 1;
                prev[i] = j;
            }
        }
    }

    int pos = 0;
    for (int i = 1; i < n; i++) {
        if (dp[i] > dp[pos]) {
            pos = i;
        }
    }

    vector<int> ans;
    while (pos != -1) {
        ans.push_back(a[pos]);
        pos = prev[pos];
    }

    reverse(ans.begin(), ans.end());
    return ans;
}
