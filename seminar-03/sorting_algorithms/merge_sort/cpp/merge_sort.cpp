#include <iostream>
#include <vector>
using namespace std;

void merge_parts(vector<long long>& a, int l, int m, int r) {
    vector<long long> buf;
    buf.reserve(r - l);

    int i = l, j = m;
    while (i < m || j < r) {
        if (i == m) buf.push_back(a[j++]);
        else if (j == r) buf.push_back(a[i++]);
        else if (a[i] <= a[j]) buf.push_back(a[i++]); // <= для устойчивости
        else buf.push_back(a[j++]);
    }

    for (int k = 0; k < (int)buf.size(); k++) {
        a[l + k] = buf[k];
    }
}

void merge_sort(vector<long long>& a, int l, int r) {
    if (r - l < 2) return;
    int m = (l + r) / 2;
    merge_sort(a, l, m);
    merge_sort(a, m, r);
    merge_parts(a, l, m, r);
}

int main() {
    int n;
    cin >> n;
    vector<long long> a(n);
    for (int i = 0; i < n; i++) cin >> a[i];

    merge_sort(a, 0, n);

    for (int i = 0; i < n; i++) {
        if (i) cout << ' ';
        cout << a[i];
    }
    cout << '\n';
    return 0;
}
