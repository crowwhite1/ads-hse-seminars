import sys
sys.setrecursionlimit(10**7)
input = sys.stdin.readline

n, m, h = map(int, input().split())
a = list(map(int, input().split()))

g = [[] for _ in range(n)]
gr = [[] for _ in range(n)]

for _ in range(m):
    u, v = map(int, input().split())
    u -= 1
    v -= 1

    if (a[u] + 1) % h == a[v]:
        g[u].append(v)
        gr[v].append(u)
    if (a[v] + 1) % h == a[u]:
        g[v].append(u)
        gr[u].append(v)

used = [False] * n
order = []

for start in range(n):
    if used[start]:
        continue

    stack = [(start, 0)]
    while stack:
        v, state = stack.pop()

        if state == 0:
            if used[v]:
                continue
            used[v] = True
            stack.append((v, 1))
            for to in g[v]:
                if not used[to]:
                    stack.append((to, 0))
        else:
            order.append(v)

used = [False] * n
comp = [-1] * n
sizes = []

for v in reversed(order):
    if used[v]:
        continue

    stack = [v]
    used[v] = True
    comp_id = len(sizes)
    sizes.append(0)

    while stack:
        cur = stack.pop()
        comp[cur] = comp_id
        sizes[comp_id] += 1

        for to in gr[cur]:
            if not used[to]:
                used[to] = True
                stack.append(to)

out = [0] * len(sizes)

for v in range(n):
    for to in g[v]:
        if comp[v] != comp[to]:
            out[comp[v]] += 1

best = -1
for i in range(len(sizes)):
    if out[i] == 0:
        if best == -1 or sizes[i] < sizes[best]:
            best = i

print(sizes[best])
print(*[i + 1 for i in range(n) if comp[i] == best])