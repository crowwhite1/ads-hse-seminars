#include <iostream>
#include <vector>
using namespace std;

void sort_rec(vector<long long>& a, vector<long long>& tmp, int l, int r) {
    if (r - l < 2) return;

    int m = (l + r) / 2;
    sort_rec(a, tmp, l, m);
    sort_rec(a, tmp, m, r);

    int i = l, j = m, k = l;
    while (i < m || j < r) {
        if (i == m) tmp[k++] = a[j++];
        else if (j == r) tmp[k++] = a[i++];
        else if (a[i] <= a[j]) tmp[k++] = a[i++]; // <= для устойчивости
        else tmp[k++] = a[j++];
    }

    for (int t = l; t < r; t++) {
        a[t] = tmp[t];
    }
}

int main() {
    int n;
    cin >> n;
    vector<long long> a(n);
    for (int i = 0; i < n; i++) cin >> a[i];

    vector<long long> tmp(n);
    sort_rec(a, tmp, 0, n);

    for (int i = 0; i < n; i++) {
        if (i) cout << ' ';
        cout << a[i];
    }
    cout << '\n';
    return 0;
}
