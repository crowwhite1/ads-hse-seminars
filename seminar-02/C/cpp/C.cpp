#include <iostream>
#include <vector>
using namespace std;

int main() {
    int n;
    cin >> n;

    vector<int> a(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    for (int i = 1; i < n; i++) {
        int tmp = a[i];
        int j = i - 1;
        bool moved = false;

        while (j >= 0 && a[j] > tmp) {
            a[j + 1] = a[j];
            j--;
            moved = true;
        }
        a[j + 1] = tmp;

        if (moved) {
            for (int k = 0; k < n; k++) {
                if (k) cout << ' ';
                cout << a[k];
            }
            cout << '\n';
        }
    }

    return 0;
}
