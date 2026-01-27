#include <iostream>
#include <vector>
using namespace std;

vector<long long> quick_sort(const vector<long long>& a) {
    if (a.size() <= 1) {
        return a;
    }

    long long pivot = a[a.size() / 2];
    vector<long long> left, mid, right;

    for (long long x : a) {
        if (x < pivot) {
            left.push_back(x);
        } else if (x == pivot) {
            mid.push_back(x);
        } else {
            right.push_back(x);
        }
    }

    vector<long long> res;
    vector<long long> l = quick_sort(left);
    vector<long long> r = quick_sort(right);

    res.insert(res.end(), l.begin(), l.end());
    res.insert(res.end(), mid.begin(), mid.end());
    res.insert(res.end(), r.begin(), r.end());

    return res;
}

int main() {
    int n;
    cin >> n;

    vector<long long> a(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    a = quick_sort(a);

    for (int i = 0; i < n; i++) {
        if (i) cout << ' ';
        cout << a[i];
    }
    cout << '\n';

    return 0;
}
