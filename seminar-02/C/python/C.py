n = int(input())
a = list(map(int, input().split()))
for i in range(1, n):
    tmp = a[i]
    j = i - 1
    flag = False

    while j >= 0 and a[j] > tmp:
        flag = True
        a[j + 1] = a[j]
        j -= 1
    a[j + 1] = tmp

    if flag:
        print(*a)