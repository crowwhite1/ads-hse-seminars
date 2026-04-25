#include <iostream>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int n;
    cin >> n;
    
    long long dp[n + 1];
    dp[n] = 1;
    dp[n - 1] = 1;
    dp[n - 2] = 2;
    
    for (int i = n - 3; i >= 0; i--) {
        dp[i] = (dp[i + 1] + dp[i + 2] + dp[i + 3]) % (1000000007LL);
    }
    
    cout << dp[0] << "\n";
    
    return 0;
}