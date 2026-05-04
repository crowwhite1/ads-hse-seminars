#include <iostream>
#include <vector>
using namespace std;

int main() {
    int n;
    cin >> n;

    for (int i = 0; i < n; i++) {
        vector<int> row(n);
        for (int j = 0; j < n; j++) {
            cin >> row[j];
        }

        vector<int> adjacent;
        for (int j = 0; j < n; j++) {
            if (row[j] == 1) {
                adjacent.push_back(j + 1);
            }
        }

        cout << adjacent.size();
        for (int v : adjacent) {
            cout << " " << v;
        }
        cout << "\n";
    }

    return 0;
}