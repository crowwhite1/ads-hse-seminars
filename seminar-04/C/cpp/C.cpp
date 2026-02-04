#include <bits/stdc++.h>
using namespace std;

static bool canFit(long m, long w, long h, long n) {
    long a = m / w; // сколько влезает по ширине
    long b = m / h; // сколько влезает по высоте
    if (a == 0 || b == 0) return false;
    return a*b >= n;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int w, h, n;
    cin >> w >> h >> n;

    long l = 0;
    long r = 100000LL * max(w, h); // безопасная верхняя граница (см. README)

    while (r - l > 1) {
        long m = l + (r - l) / 2;
        if (canFit(m, w, h, n)) r = m;
        else l = m;
    }

    cout << r << "\n";
    return 0;
}
