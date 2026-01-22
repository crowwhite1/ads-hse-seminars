# Selection Sort (сортировка выбором)
n = int(input())
a = list(map(int, input().split())) if n > 0 else []

for i in range(n):
    min_pos = i
    for j in range(i + 1, n):
        if a[j] < a[min_pos]:
            min_pos = j
    a[i], a[min_pos] = a[min_pos], a[i]

print(*a)
