import sys

sys.setrecursionlimit(10**6)

n, m = map(int, input().split())

gr = [[] for _ in range(n)]
rgr = [[] for _ in range(n)]

for _ in range(m):
    u, v = map(int, input().split())
    u -= 1
    v -= 1
    gr[u].append(v)
    rgr[v].append(u)

used = [False] * n
order = []

def dfs1(v, gr, used, order):
    used[v] = True
    for to in gr[v]:
        if not used[to]:
            dfs1(to, gr, used, order)
    order.append(v)

for v in range(n):
    if not used[v]:
        dfs1(v, gr, used, order)

order.reverse()

used = [False] * n
c_ind = [-1] * n
cnt = 0

def dfs2(v, rgr, used, c_ind, ind):
    used[v] = True
    c_ind[v] = ind
    for to in rgr[v]:
        if not used[to]:
            dfs2(to, rgr, used, c_ind, ind)

for v in order:
    if not used[v]:
        cnt += 1
        dfs2(v, rgr, used, c_ind, cnt)

print(cnt)
print(*c_ind)