n = int(input())
a = list(map(int, input().split())) if n>0 else []
swaps = 0
for i in range(n - 1):
    flag = False
    for j in range(n - 1 - i):
        if a[j] > a[j + 1]:
            a[j], a[j + 1] = a[j + 1], a[j]
            flag = True
            swaps += 1
    if not flag:
        break
print(*a)
print(swaps)