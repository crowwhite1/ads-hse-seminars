import sys
sys.setrecursionlimit(10**6)

def dfs(v, gr, color, res):
    color[v] = 1

    for to in gr[v]:
        if color[to] == 0:
            dfs(to, gr, color, res)
        elif color[to] == 1:
            res["has_cycle"] = True

    color[v] = 2
    res["order"].append(v + 1)


n, m = map(int, input().split())

gr = [[] for _ in range(n)]

for _ in range(m):
    fr, to = map(int, input().split())
    gr[fr - 1].append(to - 1)

color = [0] * n
res = {
    "has_cycle": False,
    "order": []
}

for v in range(n):
    if color[v] == 0:
        dfs(v, gr, color, res)

res["order"].reverse()

if res["has_cycle"]:
    print("No")
else:
    print("Yes")
    print(*res["order"])