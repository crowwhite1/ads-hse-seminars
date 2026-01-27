#include <iostream>
#include <vector>
using namespace std;

// Порог, ниже которого мы не продолжаем quick sort,
// а в конце добиваем всё insertion sort'ом
static const int THRESHOLD = 24;

// Сортировка вставками на отрезке [l, r)
// Очень быстра на маленьких и почти отсортированных массивах
void insertion_sort(vector<long long>& a, int l, int r) {
    for (int i = l + 1; i < r; i++) {
        long long x = a[i];
        int j = i - 1;
        while (j >= l && a[j] > x) {
            a[j + 1] = a[j];
            j--;
        }
        a[j + 1] = x;
    }
}

// Возвращает медиану из трёх значений
// Используется для более качественного выбора pivot
long long median_of_three(long long x, long long y, long long z) {
    if (x < y) {
        if (y < z) return y;
        return (x < z) ? z : x;
    } else {
        if (x < z) return x;
        return (y < z) ? z : y;
    }
}

void quick_sort(vector<long long>& a) {
    int n = (int)a.size();
    if (n <= 1) return;

    // Стек отрезков [l, r), которые ещё нужно обработать
    // Используем итеративную версию вместо рекурсии
    vector<pair<int, int>> st;
    st.reserve(64);  // Обычно глубина стека O(log n), 64 — с большим запасом

    // Кладём в стек весь массив
    st.push_back({0, n});

    // Пока есть отрезки для обработки
    while (!st.empty()) {
        // Берём очередной отрезок
        auto [l0, r0] = st.back();
        st.pop_back();
        int l = l0, r = r0;

        // Пока отрезок достаточно большой — продолжаем quick sort
        while (r - l > THRESHOLD) {

            // Выбор pivot: медиана из первого, среднего и последнего элемента
            int m = (l + r) >> 1;
            long long pivot = median_of_three(a[l], a[m], a[r - 1]);

            // 3-way partition (Dutch National Flag)
            int lt = l;        // a[l:lt] < pivot
            int i = l;         // текущий индекс
            int gt = r - 1;    // a[gt+1:r] > pivot

            while (i <= gt) {
                long long ai = a[i];
                if (ai < pivot) {
                    // переносим элемент в зону < pivot
                    a[i] = a[lt];
                    a[lt] = ai;
                    lt++;
                    i++;
                } else if (ai > pivot) {
                    // переносим элемент в зону > pivot
                    a[i] = a[gt];
                    a[gt] = ai;
                    gt--;
                } else {
                    // элемент равен pivot
                    i++;
                }
            }

            // После разбиения:
            // [l, lt)      < pivot
            // [lt, gt+1)   == pivot
            // [gt+1, r)    > pivot

            int leftL = l, leftR = lt;
            int rightL = gt + 1, rightR = r;

            // Чтобы стек оставался маленьким:
            // больший кусок кладём в стек,
            // меньший продолжаем обрабатывать сразу
            if ((leftR - leftL) < (rightR - rightL)) {
                if (rightR - rightL > THRESHOLD) {
                    st.push_back({rightL, rightR});
                }
                l = leftL;
                r = leftR;
            } else {
                if (leftR - leftL > THRESHOLD) {
                    st.push_back({leftL, leftR});
                }
                l = rightL;
                r = rightR;
            }
        }
    }

    // В конце один раз добиваем почти отсортированный массив
    // insertion sort'ом
    insertion_sort(a, 0, n);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;

    vector<long long> a(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    quick_sort(a);

    for (int i = 0; i < n; i++) {
        if (i) cout << ' ';
        cout << a[i];
    }
    cout << '\n';

    return 0;
}