#include <vector>
#include <initializer_list>
#include <utility>

template <class T>
class MinHeap {
private:
    std::vector<T> a;

    void swap_idx(int i, int j) {
        std::swap(a[i], a[j]);
    }

    void shift_up(int i) {
        while (i > 0) {
            int p = (i - 1) / 2;
            if (!(a[i] < a[p])) break;
            swap_idx(p, i);
            i = p;
        }
    }

    void shift_down(int i, int n) {
        while (true) {
            int l = 2 * i + 1;
            if (l >= n) break;

            int r = l + 1;
            int j = l;
            if (r < n && a[r] < a[l]) j = r;

            if (!(a[j] < a[i])) break;
            swap_idx(i, j);
            i = j;
        }
    }

    void heapify() {
        int n = (int)a.size();
        for (int i = n / 2 - 1; i >= 0; --i) {
            shift_down(i, n);
        }
    }

public:
    MinHeap() = default;

    explicit MinHeap(const std::vector<T>& data) : a(data) {
        heapify();
    }

    MinHeap(std::initializer_list<T> init) : a(init) {
        heapify();
    }

    int size() const {
        return (int)a.size();
    }

    bool empty() const {
        return a.empty();
    }

    const T* peek() const {
        return a.empty() ? nullptr : &a[0];
    }

    void push(const T& x) {
        a.push_back(x);
        shift_up((int)a.size() - 1);
    }

    bool pop(T& out) {
        if (a.empty()) return false;

        out = a[0];
        T last = a.back();
        a.pop_back();
        if (!a.empty()) {
            a[0] = std::move(last);
            shift_down(0, (int)a.size());
        }
        return true;
    }
};