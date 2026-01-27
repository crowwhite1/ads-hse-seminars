def quick_sort(a, l, r):
    if r - l < 2:
        return

    pivot = a[(l + r) // 2]   # детерминированный pivot
    i = l
    j = l

    # Разделяем массив на три группы:
    # a[l:i)  - < pivot
    # a[i:j)  - == pivot
    # a[j:r)  - > pivot
    for k in range(l, r):
        if a[k] < pivot:
            a[k], a[i] = a[i], a[k]
            if j != i:
                a[k], a[j] = a[j], a[k]
            i += 1
            j += 1
        elif a[k] == pivot:
            a[k], a[j] = a[j], a[k]
            j += 1

    quick_sort(a, l, i)
    quick_sort(a, j, r)


n = int(input())
a = list(map(int, input().split())) if n > 0 else []
quick_sort(a, 0, n)
print(*a)
