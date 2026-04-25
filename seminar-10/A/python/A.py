import sys
sys.setrecursionlimit(10**6)

def dfs(v, gr, used, order):
    used[v] = True
    for to in gr[v]:
        if not used[to]:
            dfs(to, gr, used, order)
    order.append(v + 1)

n = int(input())
p = list(map(int, input().split()))
gr = [[] for _ in range(n)]

for fr in range(n):
    tmp = list(map(int, input().split()))
    for to in tmp[1:]:
        gr[fr].append(to - 1)

used = [False] * n
order = []

dfs(0, gr, used, order)

ans = 0

for v in order:
    ans += p[v - 1]

print(ans, len(order))
print(*order)