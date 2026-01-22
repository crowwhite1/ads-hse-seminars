# Bubble Sort (оптимизированная версия: ранний выход через флаг)
n = int(input())
a = list(map(int, input().split())) if n > 0 else []

for i in range(n - 1):
    swapped = False
    for j in range(n - 1 - i):
        if a[j] > a[j + 1]:
            a[j], a[j + 1] = a[j + 1], a[j]
            swapped = True
    if not swapped:
        break

print(*a)
