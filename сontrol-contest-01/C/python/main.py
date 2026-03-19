import bisect
import math
import sys

input = sys.stdin.readline

t = int(input())
out = []

for _ in range(t):
    n, m = map(int, input().split())

    k = list(int(input()) for i in range(n))
    k.sort()

    p = []
    for _ in range(m):
        a, b, c = map(int, input().split())
        p.append((a, b, c))

    for a, b, c in p:
        if a * c < 0:
            out.append('NO')
            continue
        val = 2.0 * math.sqrt(a * c)
        kx_m = b - val
        kx_p = b + val

        pos = bisect.bisect_right(k, kx_m)

        if pos != len(k):
            ans = k[pos]
            if ans < kx_p:
                out.append("YES")
                out.append(str(ans))
            else:
                out.append("NO")
        else:
            out.append("NO")

    out.append("")

sys.stdout.write("\n".join(out))