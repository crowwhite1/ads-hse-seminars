#include <bits/stdc++.h>
using namespace std;

struct IntPairList {
    vector<int> x, y;

    void add(int a, int b) {
        x.push_back(a);
        y.push_back(b);
    }

    int size() const {
        return (int)x.size();
    }
};

void swap_values(vector<int>& arr, int i, int j) {
    swap(arr[i], arr[j]);
}

bool badTriple(const vector<int>& a) {
    return (a[0] == 1 && a[1] == 3 && a[2] == 2) ||
           (a[0] == 3 && a[1] == 1 && a[2] == 2) ||
           (a[0] == 3 && a[1] == 2 && a[2] == 1);
}

void testCase(string& out) {
    int n;
    cin >> n;

    vector<int> a(n);
    vector<int> p(n + 1);

    for (int i = 0; i < n; i++) {
        cin >> a[i];
        p[a[i]] = i;
    }

    if (n == 3 && badTriple(a)) {
        out += "-1\n";
        return;
    }

    int half = n >> 1;
    IntPairList ans;

    int ind = p[1];

    while (ind < n - 1) {
        int target;
        if (ind + half < n) {
            target = ind + half;
            int val = a[target];
            ans.add(p[1] + 1, p[val] + 1);
            swap_values(p, 1, val);
            swap_values(a, p[1], p[val]);
            ind += half;
        } else {
            target = n - 1;
            int val = a[target];
            ans.add(p[1] + 1, p[val] + 1);
            swap_values(p, 1, val);
            swap_values(a, p[1], p[val]);
            ind = n - 1;
        }
    }

    int j = 1;
    while (ind > 0) {
        int to = p[ind + 1];
        int to2 = 0;

        while (ind > to) {
            if (ind - half > to) {
                int v1 = a[ind];
                int v2 = a[ind - half];
                ans.add(p[v1] + 1, p[v2] + 1);
                swap_values(p, v1, v2);
                swap_values(a, p[v1], p[v2]);
                ind -= half;
            } else {
                int target = to;
                int v1 = a[ind];
                int v2 = a[target];
                ans.add(p[v1] + 1, p[v2] + 1);
                swap_values(p, v1, v2);
                swap_values(a, p[v1], p[v2]);
                to2 = p[a[ind]];
                ind = to;
            }
        }

        while (ind <= n - j - 1) {
            if (ind < to2) {
                if (ind == n - j - 1) {
                    j++;
                    break;
                }

                int shiftTarget = ind + (to2 - ind) - 1;

                if (p[a[ind]] != p[a[shiftTarget]]) {
                    int v1 = a[ind];
                    int v2 = a[shiftTarget];
                    ans.add(p[v1] + 1, p[v2] + 1);
                    swap_values(p, v1, v2);
                    swap_values(a, p[v1], p[v2]);
                    ind = shiftTarget;
                }

                if (ind + half > n - j - 1) {
                    int target = n - j - 1;
                    if (p[a[ind]] != p[a[target]]) {
                        int v1 = a[ind];
                        int v2 = a[target];
                        ans.add(p[v1] + 1, p[v2] + 1);
                        swap_values(p, v1, v2);
                        swap_values(a, p[v1], p[v2]);
                        ind = target;
                    }
                } else {
                    int target = ind + half;
                    int v1 = a[ind];
                    int v2 = a[target];
                    ans.add(p[v1] + 1, p[v2] + 1);
                    swap_values(p, v1, v2);
                    swap_values(a, p[v1], p[v2]);
                    ind += half;
                }
            } else {
                if (ind + 1 > n - j) {
                    int v1 = a[ind];
                    int v2 = a[to2];
                    ans.add(p[v1] + 1, p[v2] + 1);
                    swap_values(p, v1, v2);
                    swap_values(a, p[v1], p[v2]);

                    int tmp = ind;
                    ind = to2;
                    to2 = tmp;

                    v1 = a[ind];
                    v2 = a[to2 - 1];
                    ans.add(p[v1] + 1, p[v2] + 1);
                    swap_values(p, v1, v2);
                    swap_values(a, p[v1], p[v2]);

                    ind = n - j - 1;
                    j++;
                    break;
                } else {
                    int v1 = a[ind];
                    int v2 = a[ind + 1];
                    ans.add(p[v1] + 1, p[v2] + 1);
                    swap_values(p, v1, v2);
                    swap_values(a, p[v1], p[v2]);

                    ind++;

                    v1 = a[ind];
                    v2 = a[to2];
                    ans.add(p[v1] + 1, p[v2] + 1);
                    swap_values(p, v1, v2);
                    swap_values(a, p[v1], p[v2]);

                    int tmp = ind;
                    ind = to2;
                    to2 = tmp;
                }
            }
        }
    }

    int m = ans.size();

    if (m == 0) {
        out += "0\n";
        return;
    }

    if (m == 1) {
        out += "1\n";
        out += to_string(ans.x[0]) + " " + to_string(ans.y[0]) + "\n";
        return;
    }

    IntPairList res;
    for (int i = 0; i < m - 1; i++) {
        if (ans.x[i] == ans.y[i + 1] && ans.y[i] == ans.x[i + 1]) {
            i++;
        } else {
            res.add(ans.x[i], ans.y[i]);
        }
    }

    if (ans.x[m - 2] != ans.y[m - 1] || ans.y[m - 2] != ans.x[m - 1]) {
        res.add(ans.x[m - 1], ans.y[m - 1]);
    }

    if (res.size() > 4 * n) {
        out += "-1\n";
    } else {
        out += to_string(res.size()) + "\n";
        for (int i = 0; i < res.size(); i++) {
            out += to_string(res.x[i]) + " " + to_string(res.y[i]) + "\n";
        }
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int t;
    cin >> t;

    string out;
    while (t--) {
        testCase(out);
    }

    cout << out;
    return 0;
}