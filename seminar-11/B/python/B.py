n, m = map(int, input().split())

c = [[0] * m for _ in range(n)]
for i in range(n):
    c[i] = list(map(int, input().split()))

a = [[0] * m for _ in range(n)]
a[0][0] = c[0][0]

for i in range(1, n):
    a[i][0] = a[i - 1][0] + c[i][0]

for j in range(1, m):
    a[0][j] = a[0][j - 1] + c[0][j]

for i in range(1, n):
    for j in range(1, m):
        a[i][j] = max(a[i - 1][j], a[i][j - 1]) + c[i][j]

print(a[n - 1][m - 1])

i = n - 1
j = m - 1
path = []

while not (i == 0 and j == 0):
    if i == 0:
        path.append('R')
        j -= 1
    elif j == 0:
        path.append('D')
        i -= 1
    else:
        if a[i - 1][j] > a[i][j - 1]:
            path.append('D')
            i -= 1
        else:
            path.append('R')
            j -= 1

print(*reversed(path))