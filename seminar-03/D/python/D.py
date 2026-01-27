def quick_sort(a):
    if len(a) <= 1:
        return a

    pivot = a[len(a) // 2]
    left = [x for x in a if x < pivot]
    mid = [x for x in a if x == pivot]
    right = [x for x in a if x > pivot]

    return quick_sort(left) + mid + quick_sort(right)


n = int(input())
a = list(map(int, input().split()))
a = quick_sort(a)
unique = 1
for i in range(1, n):
    if a[i] != a[i - 1]:
        unique += 1
print(unique)
