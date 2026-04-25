n = int(input())
w = int(input())
p = list(map(int, input().split()))
c = list(map(int, input().split()))

a = [[0] * (w + 1) for _ in range(n + 1)]

for i in range(1, n + 1):
    for j in range(1, w + 1):
        if p[i - 1] > j:
            a[i][j] = a[i - 1][j]
        else:
            a[i][j] = max(a[i - 1][j], a[i - 1][j - p[i - 1]] + c[i - 1])

i = n
j = w
backpack = []

while i != 0 and j != 0:
    if p[i - 1] > j:
        i -= 1
    else:
        if a[i - 1][j] > a[i - 1][j - p[i - 1]] + c[i - 1]:
            i -= 1
        else:
            backpack.append(i)
            j -= p[i - 1]
            i -= 1

print(sum([c[i - 1] for i in backpack]))
print(sum([p[i - 1] for i in backpack]))
print(len(backpack))
print(*[p[i - 1] for i in backpack])
print(*[c[i - 1] for i in backpack])