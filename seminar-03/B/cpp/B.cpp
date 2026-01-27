#include <iostream>
#include <vector>
using namespace std;

void merge_sort(vector<long long>& a, vector<long long>& buf, int l, int r);
void merge_parts(vector<long long>& a, vector<long long>& buf, int l, int m, int r);

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;

    vector<long long> a(n);
    for (int i = 0; i < n; i++) cin >> a[i];

    vector<long long> buf(n);
    merge_sort(a, buf, 0, n);

    for (int i = 0; i < n; i++) {
        if (i) cout << ' ';
        cout << a[i];
    }
    return 0;
}

void merge_sort(vector<long long>& a, vector<long long>& buf, int l, int r) {
    if (r - l < 2) return;
    int m = (l + r) / 2;
    merge_sort(a, buf, l, m);
    merge_sort(a, buf, m, r);
    merge_parts(a, buf, l, m, r);
}

void merge_parts(vector<long long>& a, vector<long long>& buf, int l, int m, int r) {
    int i = l, j = m, k = 0;

    while (i < m || j < r) {
        if (i == m) buf[k++] = a[j++];
        else if (j == r) buf[k++] = a[i++];
        else if (a[i] <= a[j]) buf[k++] = a[i++]; // стабильность
        else buf[k++] = a[j++];
    }

    for (int t = 0; t < k; t++) a[l + t] = buf[t];
}
