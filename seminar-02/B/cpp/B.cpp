#include <iostream>
#include <vector>
using namespace std;

int main() {
    int n;
    cin >> n;

    vector<int> a(n);
    for (int i = 0; i < n; i++) cin >> a[i];

    int count = 0;

    for (int i = 1; i < n; i++) {
        int key = a[i];
        int j = i - 1;
        bool moved = false;

        while (j >= 0 && a[j] > key) {
            a[j + 1] = a[j];
            j--;
            moved = true;
        }

        a[j + 1] = key;

        if (!moved) {
            count++;
        }
    }

    for (int i = 0; i < n; i++) {
        if (i) cout << ' ';
        cout << a[i];
    }
    cout << "\n" << count;

    return 0;
}
