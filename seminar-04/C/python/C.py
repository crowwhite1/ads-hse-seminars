w, h, n = map(int, input().split())
l = 0
r = 100_000 * max(w, h)
while r - l > 1:
    m = (l + r) // 2
    if (m // w) * (m // h) >= n:
        r = m
    else:
        l = m
print(r)