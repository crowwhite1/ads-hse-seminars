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


n, m = map(int, input().split())
a = list(map(int, input().split()))
b = list(map(int, input().split()))
for target in b:
    l = lbound(target, a)
    r = rbound(target, a)
    if l > r:
        print(0)
    else:
        print(f"{l + 1} {r + 1}")
