x = input()
y = input()

a = [[0] * (len(y) + 1) for _ in range(len(x) + 1)]

for i in range(len(x)):
    for j in range(len(y)):
        if x[i] == y[j]:
            a[i + 1][j + 1] = a[i][j] + 1
        else:
            a[i + 1][j + 1] = max(a[i][j + 1], a[i + 1][j])

print(a[len(x)][len(y)])