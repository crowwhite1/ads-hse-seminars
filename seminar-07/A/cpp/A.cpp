#include <bits/stdc++.h>
using namespace std;

struct MaxHeap {
    vector<int> a;

    bool is_empty() const { return a.empty(); }

    void push(int x) {
        a.push_back(x);
        shift_up((int)a.size() - 1);
    }

    int pop() {
        int top = a[0];
        int last = a.back();
        a.pop_back();
        if (!a.empty()) {
            a[0] = last;
            shift_down(0);
        }
        return top;
    }

    void shift_up(int i) {
        while (i > 0) {
            int p = (i - 1) / 2;
            if (a[p] >= a[i]) break;
            swap(a[p], a[i]);
            i = p;
        }
    }

    void shift_down(int i) {
        int n = (int)a.size();
        while (true) {
            int l = 2 * i + 1;
            if (l >= n) break;
            int r = l + 1;
            int j = l;
            if (r < n && a[r] > a[l]) j = r;
            if (a[i] >= a[j]) break;
            swap(a[i], a[j]);
            i = j;
        }
    }
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;

    MaxHeap heap;
    string cmd;
    vector<int> ans;
    ans.reserve(n);

    for (int i = 0; i < n; i++) {
        cin >> cmd;
        if (cmd == "0") {
            int x;
            cin >> x;
            heap.push(x);
        } else { // "1"
            ans.push_back(heap.pop());
        }
    }

    for (int x : ans) {
        cout << x << "\n";
    }
    return 0;
}