#include <iostream>
#include <vector>
using namespace std;

void quick_sort(vector<long long>& a, int l, int r) {
    if (r - l < 2) return;

    long long pivot = a[(l + r) / 2];
    int i = l;
    int j = l;

    for (int k = l; k < r; k++) {
        if (a[k] < pivot) {
            swap(a[k], a[i]);
            if (j != i) swap(a[k], a[j]);
            i++;
            j++;
        } else if (a[k] == pivot) {
            swap(a[k], a[j]);
            j++;
        }
    }

    quick_sort(a, l, i);
    quick_sort(a, j, r);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;

    vector<long long> a(n);
    for (int i = 0; i < n; i++) cin >> a[i];

    quick_sort(a, 0, n);

    for (int i = 0; i < n; i++) {
        if (i) cout << ' ';
        cout << a[i];
    }
    return 0;
}
