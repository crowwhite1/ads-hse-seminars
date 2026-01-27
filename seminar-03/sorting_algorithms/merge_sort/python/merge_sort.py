def merge(a, l, m, r):
    buf = []
    i, j = l, m
    while i < m or j < r:
        if i == m:
            buf.append(a[j]); j += 1
        elif j == r:
            buf.append(a[i]); i += 1
        elif a[i] <= a[j]:  # <= для устойчивости
            buf.append(a[i]); i += 1
        else:
            buf.append(a[j]); j += 1

    for k in range(len(buf)):
        a[l + k] = buf[k]


def merge_sort(a, l, r):
    if r - l < 2:
        return
    m = (l + r) // 2
    merge_sort(a, l, m)
    merge_sort(a, m, r)
    merge(a, l, m, r)


n = int(input())
a = list(map(int, input().split())) if n > 0 else []
merge_sort(a, 0, n)
print(*a)
