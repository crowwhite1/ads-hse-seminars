n = int(input())
a = list(map(int, input().split())) if n > 0 else []
count = 0
for i in range(1, n):
    key = a[i]
    j = i - 1
    moved = False

    while j >= 0 and a[j] > key:
        a[j + 1] = a[j]
        j -= 1
        moved = True

    a[j + 1] = key

    if not moved:
        count += 1

print(*a)
print(count)
