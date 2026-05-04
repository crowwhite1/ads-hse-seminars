#include <bits/stdc++.h>
using namespace std;
#define ll long long
#define vec vector

struct node {
    ll a, b, c;
};

int main(){
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int t = 1; cin >> t;
    while(t-->0){
        ll n, m; cin >> n >> m;
        vec<ll> k(n);
        for(int i = 0; i < n; i++){
            cin >> k[i];
        }
        sort(k.begin(), k.end());
        vec<node> p(m);
        for(int i = 0; i < m; i++){
            cin >> p[i].a >> p[i].b >> p[i].c;
        }
        for(int i = 0; i < m; i++){
            double long kx_m = p[i].b - (pow((4 * p[i].b * p[i].b - 4 * (p[i].b * p[i].b - 4 * p[i].a * p[i].c)),  0.5)) / 2;
            double long kx_p = p[i].b + (pow((4 * p[i].b * p[i].b - 4 * (p[i].b * p[i].b - 4 * p[i].a * p[i].c)),  0.5)) / 2;
            if (upper_bound(k.begin(), k.end(), kx_m) != k.end()){
                ll ans = *upper_bound(k.begin(), k.end(), kx_m);
                if (ans < kx_p) cout << "YES" << '\n' << ans << '\n';
                else cout << "NO" << '\n';
            }else {
                cout << "NO" << '\n';
            }
        }
        cout << '\n';
    }
}