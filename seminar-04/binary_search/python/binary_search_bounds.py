# lbound: первый индекс i, такой что a[i] >= target
# возвращает значение в диапазоне [0..n]
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

# rbound: последний индекс i, такой что a[i] <= target
# возвращает значение в диапазоне [-1..n-1]
# (эквивалент: (первый индекс > target) - 1)
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