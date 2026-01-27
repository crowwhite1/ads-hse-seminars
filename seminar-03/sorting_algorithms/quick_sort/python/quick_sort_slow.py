def quick_sort(a):
    if len(a) <= 1:
        return a

    pivot = a[len(a) // 2]
    left = [x for x in a if x < pivot]
    mid  = [x for x in a if x == pivot]
    right = [x for x in a if x > pivot]

    return quick_sort(left) + mid + quick_sort(right)


n = int(input())
a = list(map(int, input().split())) if n > 0 else []
a = quick_sort(a)
print(*a)