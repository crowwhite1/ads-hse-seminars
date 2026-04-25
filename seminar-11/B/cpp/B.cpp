#include <iostream>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int n, m;
    cin >> n >> m;
    
    vector<vector<int>> c(n, vector<int>(m));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> c[i][j];
        }
    }
    
    vector<vector<int>> a(n, vector<int>(m));
    a[0][0] = c[0][0];
    
    for (int i = 1; i < n; i++) {
        a[i][0] = a[i - 1][0] + c[i][0];
    }
    
    for (int j = 1; j < m; j++) {
        a[0][j] = a[0][j - 1] + c[0][j];
    }
    
    for (int i = 1; i < n; i++) {
        for (int j = 1; j < m; j++) {
            a[i][j] = max(a[i - 1][j], a[i][j - 1]) + c[i][j];
        }
    }
    
    cout << a[n - 1][m - 1] << "\n";
    
    int i = n - 1;
    int j = m - 1;
    vector<string> path;
    
    while (!(i == 0 && j == 0)) {
        if (i == 0) {
            path.push_back("R");
            j -= 1;
        } else if (j == 0) {
            path.push_back("D");
            i -= 1;
        } else {
            if (a[i - 1][j] > a[i][j - 1]) {
                path.push_back("D");
                i -= 1;
            } else {
                path.push_back("R");
                j -= 1;
            }
        }
    }
    
    for (int k = path.size() - 1; k >= 0; k--) {
        cout << path[k] << " ";
    }
    cout << "\n";
    
    return 0;
}