def merge_sort(a):
    n = len(a)
    buf = [0] * n

    def sort(l, r):
        if r - l < 2:
            return
        m = (l + r) // 2
        sort(l, m)
        sort(m, r)

        i, j, k = l, m, l
        while i < m or j < r:
            if i == m:
                buf[k] = a[j]; j += 1
            elif j == r:
                buf[k] = a[i]; i += 1
            elif a[i] <= a[j]:  # <= для устойчивости
                buf[k] = a[i]; i += 1
            else:
                buf[k] = a[j]; j += 1
            k += 1

        for t in range(l, r):
            a[t] = buf[t]

    sort(0, n)


n = int(input())
a = list(map(int, input().split())) if n > 0 else []
merge_sort(a)
print(*a)
