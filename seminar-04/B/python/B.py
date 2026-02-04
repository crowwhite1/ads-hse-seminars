def lbound(target, elems):
    l = -1
    r = len(elems)
    while r - l > 1:
        m = (l + r) // 2
        if elems[m] >= target:
            r = m
        else:
            l = m
    return r


def rbound(target, elems):
    l = -1
    r = len(elems)
    while r - l > 1:
        m = (l + r) // 2
        if elems[m] > target:
            r = m
        else:
            l = m
    return l


n, k = map(int, input().split())
a = list(map(int, input().split()))
b = list(map(int, input().split()))
for target in b:
    l = lbound(target, a)
    r = rbound(target, a)
    if l <= r:
        print(target)
    else:
        if l >= len(a):
            # Ближайшего числа, большего target, не существует - выводим ближайшее меньшее
            print(a[r])
        elif r < 0:
            # Ближайшего числа, меньшего target, не существует - выводим ближайшее большее
            print(a[l])
        elif a[l] - target < target - a[r]:
            # Выбираем из двух ближайших самое ближайшее
            # Если оба числа одинаково близки, то выведем меньшее - т.е. a[r]
            print(a[l])
        else:
            print(a[r])
