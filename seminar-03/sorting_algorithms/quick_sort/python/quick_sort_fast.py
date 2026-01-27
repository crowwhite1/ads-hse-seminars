import random

THRESHOLD = 24

def insertion_sort(a, l=0, r=None):
    """Сортировка вставками на отрезке [l, r)."""
    if r is None:
        r = len(a)
    for i in range(l + 1, r):
        x = a[i]
        j = i - 1
        while j >= l and a[j] > x:
            a[j + 1] = a[j]
            j -= 1
        a[j + 1] = x


def quick_sort(a):
    """
    Итеративный quicksort с:
    - pivot = median-of-3 (a[l], a[mid], a[r-1])
    - 3-way partition (Dutch National Flag)
    - маленькие куски не сортируем сразу, а добиваем ОДИН раз insertion sort'ом в конце
    - стек держим маленьким: больший кусок пушим, меньший обрабатываем сразу
    """
    n = len(a)
    if n <= 1:
        return

    stack = [(0, n)]
    append = stack.append
    pop = stack.pop

    while stack:
        l, r = pop()

        while r - l > THRESHOLD:
            pivot = a[random.randrange(l, r)]

            # 3-way partition:
            lt = l          # a[l:lt] < pivot
            i = l           # текущий индекс
            gt = r - 1      # a[gt+1:r] > pivot

            while i <= gt:
                ai = a[i]
                if ai < pivot:
                    a[lt], a[i] = ai, a[lt]
                    lt += 1
                    i += 1
                elif ai > pivot:
                    a[i], a[gt] = a[gt], ai
                    gt -= 1
                else:
                    i += 1

            # [l, lt) < pivot
            # [lt, gt+1) == pivot
            # [gt+1, r) > pivot

            left_l, left_r = l, lt
            right_l, right_r = gt + 1, r

            # Меньший кусок обрабатываем сразу, больший — в стек
            if (left_r - left_l) < (right_r - right_l):
                if right_r - right_l > THRESHOLD:
                    append((right_l, right_r))
                l, r = left_l, left_r
            else:
                if left_r - left_l > THRESHOLD:
                    append((left_l, left_r))
                l, r = right_l, right_r

    # Один раз добиваем почти-отсортированный массив
    insertion_sort(a, 0, n)


# ---- I/O ----
n = int(input())
a = list(map(int, input().split())) if n > 0 else []
quick_sort(a)
print(*a)
