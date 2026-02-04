#include <iostream>
#include <vector>
using namespace std;

// first index i such that a[i] >= target
int lbound(const vector<int>& a, int target) {
    int l = -1, r = (int)a.size();
    while (r - l > 1) {
        int m = l + (r - l) / 2;
        if (a[m] >= target) r = m;
        else l = m;
    }
    return r;
}

// last index i such that a[i] <= target (or -1)
int rbound(const vector<int>& a, int target) {
    int l = -1, r = (int)a.size();
    while (r - l > 1) {
        int m = l + (r - l) / 2;
        if (a[m] > target) r = m;
        else l = m;
    }
    return l;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m;
    cin >> n >> m;

    vector<int> a(n), b(m);
    for (int i = 0; i < n; i++) cin >> a[i];
    for (int i = 0; i < m; i++) cin >> b[i];

    for (int i = 0; i < m; i++) {
        int target = b[i];
        int l = lbound(a, target);
        int r = rbound(a, target);
        if (l > r) cout << 0 << "\n";
        else cout << (l + 1) << " " << (r + 1) << "\n"; // 1-based
    }
    return 0;
}
