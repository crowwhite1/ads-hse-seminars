#include <vector>
#include <optional>
#include <initializer_list>
#include <utility>

template <class T>
class MinHeap {
public:
    std::vector<T> a;

    MinHeap() = default;

    MinHeap(const std::vector<T>& data) {
        a = data;
        heapify();
    }

    MinHeap(std::initializer_list<T> init) {
        a = init;
        heapify();
    }

    size_t size() const {
        return a.size();
    }

    bool is_empty() const {
        return a.empty();
    }

    std::optional<T> peek() const {
        if (a.empty()) return std::nullopt;
        return a[0];
    }

    void push(const T& x) {
        a.push_back(x);
        shift_up((int)a.size() - 1);
    }

    std::optional<T> pop() {
        if (a.empty()) return std::nullopt;
        T top = a[0];
        T last = a.back();
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
            if (a[p] <= a[i]) break;
            std::swap(a[p], a[i]);
            i = p;
        }
    }

    void shift_down(int i, int n = -1) {
        if (n == -1) n = (int)a.size();
        while (true) {
            int l = 2 * i + 1;
            if (l >= n) break;
            int r = l + 1;
            int j = l;
            if (r < n && a[r] < a[l]) j = r;
            if (a[i] <= a[j]) break;
            std::swap(a[i], a[j]);
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

template <class T>
std::vector<T> heap_sort_new_array(const std::vector<T>& arr) {
    MinHeap<T> heap(arr);
    std::vector<T> result;
    result.reserve(arr.size());
    while (!heap.is_empty()) {
        result.push_back(*heap.pop());
    }
    return result;
}