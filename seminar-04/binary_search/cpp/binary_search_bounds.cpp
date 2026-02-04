#include <vector>
using namespace std;

// lbound: первый индекс i, такой что a[i] >= target
// возвращает значение в диапазоне [0..n]
int lbound(const vector<int>& a, int target) {
    int l = -1;
    int r = (int)a.size();
    while (r - l > 1) {
        int m = l + (r - l) / 2;
        if (a[m] >= target) {
            r = m;
        } else {
            l = m;
        }
    }
    return r;
}

// rbound: последний индекс i, такой что a[i] <= target
// возвращает значение в диапазоне [-1..n-1]
int rbound(const vector<int>& a, int target) {
    int l = -1;
    int r = (int)a.size();
    while (r - l > 1) {
        int m = l + (r - l) / 2;
        if (a[m] > target) {
            r = m;
        } else {
            l = m;
        }
    }
    return l;
}
