#include <iostream>
#include <vector>
#include <random>
using namespace std;

// Порог, ниже которого мы не продолжаем quick sort,
// а в конце добиваем всё insertion sort'ом
static const int THRESHOLD = 24;

// Сортировка вставками на [l, r)
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

// Итеративный quick sort:
// - pivot выбирается случайно
// - 3-way partition (Dutch National Flag)
// - меньший кусок обрабатываем сразу, больший пушим в стек
// - в конце один insertion sort по всему массиву
void quick_sort(vector<long long>& a) {
    int n = (int)a.size();
    if (n <= 1) return;

    // Генератор случайных чисел
    // Фиксированный seed → детерминированное поведение
    static std::mt19937 rng(123456);

    vector<pair<int,int>> st;
    st.reserve(64);          // обычно глубины хватает с большим запасом
    st.push_back({0, n});

    // Пока есть отрезки для обработки
    while (!st.empty()) {
        // Берём очередной отрезок
        auto [l0, r0] = st.back();
        st.pop_back();
        int l = l0, r = r0;

        while (r - l > THRESHOLD) {
            // случайный pivot из [l, r)
            uniform_int_distribution<int> dist(l, r - 1);
            long long pivot = a[dist(rng)];

            // 3-way partition
            int lt = l;       // a[l:lt] < pivot
            int i  = l;       // текущий индекс
            int gt = r - 1;   // a[gt+1:r] > pivot

            while (i <= gt) {
                long long ai = a[i];
                if (ai < pivot) {
                    a[i] = a[lt];
                    a[lt] = ai;
                    lt++;
                    i++;
                } else if (ai > pivot) {
                    a[i] = a[gt];
                    a[gt] = ai;
                    gt--;
                } else {
                    i++;
                }
            }

            // [l, lt)      < pivot
            // [lt, gt+1)   == pivot
            // [gt+1, r)    > pivot

            int leftL = l, leftR = lt;
            int rightL = gt + 1, rightR = r;

            // меньший кусок — сразу, больший — в стек
            if ((leftR - leftL) < (rightR - rightL)) {
                if (rightR - rightL > THRESHOLD)
                    st.push_back({rightL, rightR});
                l = leftL;
                r = leftR;
            } else {
                if (leftR - leftL > THRESHOLD)
                    st.push_back({leftL, leftR});
                l = rightL;
                r = rightR;
            }
        }
    }

    // один финальный проход insertion sort'ом
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
