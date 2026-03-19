#include <bits/stdc++.h>
//#include "testlib.h"
using namespace std;
#define ll long long
#define vec vector

const ll INF = 1e9 + 7;
const string YES = "YES";
const string NO = "NO";

void test_case(int argc, char *argv[]){
    int n, k; cin >> n >> k;
    vec<int> a(n);
    for(int i = 0; i < n; i++){
        cin >> a[i];
    }
    sort(a.begin(), a.end());
    int mx = max(abs(a[0]), a[n - 1]);
    ll ans = 0;
    for(int i = 0; i < n; i++){
        if (k){
            ans += mx;
            k--;
        }else ans += a[i];
    }
    cout <<  ans;
}

int main(int argc, char *argv[]){
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int t = 1;// cin >> t;
    while(t-->0){
        test_case(argc, argv);
        cout << '\n';
    }
}
