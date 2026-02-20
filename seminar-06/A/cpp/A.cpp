#include <iostream>
#include <vector>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;

    vector<vector<int>> arr(n, vector<int>(n));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> arr[i][j];
        }
    }

    for (int i = 0; i < n; i++) {
        int cnt = 0;
        for (int j = 0; j < n; j++) {
            if (arr[i][j] == 1) cnt++;
        }

        if (cnt == 0) {
            cout << 0 << "\n";
        } else {
            cout << cnt;
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 1) {
                    cout << ' ' << (j + 1); // вершины в выводе 1..n
                }
            }
            cout << "\n";
        }
    }

    return 0;
}