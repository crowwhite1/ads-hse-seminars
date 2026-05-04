#include <bits/stdc++.h>

using namespace std;

#define ll long long
#define vec vector
#define all(x) (x).begin(), (x).end()
#define bucket_size 100003

const ll INF = 1e18;
const int INF_1e9 = 1e9;

int mod = (int)1e9 + 7;

void test_case(int argc, char *argv[]) {
    ll n, k;
    cin >> n >> k;
    vec<pair<ll, int>> a(n);
    ll mx_ans = 0;
    for (int i = 0; i < n; i++) {
        cin >> a[i].first;
        a[i].second = i;
        mx_ans = max(a[i].first, mx_ans);
    }
    sort(all(a));
    int j = 1;
    ll mx = a[0].first;
    ll sm = 0;
    while (k && j < n) {
        //        cout << a[j].first << ' ' << a[j - 1].first << '\n';
        if ((a[j].first - a[j - 1].first) * j + sm <= k) {
            sm += (a[j].first - a[j - 1].first) * j;
            mx = a[j].first;
        } else {
            break;
        }
        j++;
    }
    for (int i = 0; i < j; i++) {
        a[i].first = mx;
    }
    if (j <= n - 1) {
        ll L = 0, R = k - sm + 1;
        // cout << R << '\n';
        while (R - L > 1) {
            ll m = (L + R) / 2;
            if (m * j <= k - sm) {
                L = m;
            } else {
                R = m;
            }
        }
        for (int i = 0; i < j; i++) {
            a[i].first += L;
        }
        // cout << L << '\n';
        cout << mx_ans - L - mx << '\n';
        
    }
    sort(all(a), [](const pair<ll, int> &a, const pair<ll, int> &b) { return a.second < b.second; });
    //    cout << k - R - sm << '\n';
    if (n == j) cout << mx_ans - mx << '\n';
    for (int i = 0; i < n; i++) {
        cout << a[i].first << ' ';
    }
}


int main(int argc, char *argv[]) {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    //       freopen("tests/01", "r", stdin);
    //       freopen("answers/01.out", "w", stdout);
    int t = 1; // cin >> t;
    cout << fixed << setprecision(0);
    while (t-- > 0) {
        test_case(argc, argv);
        cout.flush();
    }
    return 0;
}