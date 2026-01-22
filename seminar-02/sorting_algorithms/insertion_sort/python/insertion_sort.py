# Insertion Sort (простыми вставками)
n = int(input())
a = list(map(int, input().split())) if n > 0 else []

for i in range(1, n):
    key = a[i]
    j = i - 1
    while j >= 0 and a[j] > key:
        a[j + 1] = a[j]
        j -= 1
    a[j + 1] = key

print(*a)
