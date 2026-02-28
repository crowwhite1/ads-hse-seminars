#include <bits/stdc++.h>
using namespace std;

struct MinHeap {
    vector<int> a;

    MinHeap(const vector<int>& data) : a(data) {
        heapify();
    }

    bool is_empty() const { return a.empty(); }

    int pop() {
        int top = a[0];
        int last = a.back();
        a.pop_back();
        if (!a.empty()) {
            a[0] = last;
            shift_down(0, (int)a.size());
        }
        return top;
    }

    void shift_down(int i, int n) {
        while (true) {
            int l = 2 * i + 1;
            if (l >= n) break;
            int r = l + 1;
            int j = l;
            if (r < n && a[r] < a[l]) j = r;
            if (a[i] <= a[j]) break;
            swap(a[i], a[j]);
            i = j;
        }
    }

    void heapify() {
        int n = (int)a.size();
        for (int i = n / 2 - 1; i >= 0; --i) {
            shift_down(i, n);
        }
    }
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;
    vector<int> arr(n);
    for (int i = 0; i < n; i++) cin >> arr[i];

    MinHeap heap(arr);

    for (int i = 0; i < n; i++) {
        if (i) cout << ' ';
        cout << heap.pop();
    }
    return 0;
}