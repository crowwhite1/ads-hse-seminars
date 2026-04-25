import sys

sys.setrecursionlimit(10**6)


# DFS считает tin/low и находит мосты (в bridges складываем пары (v, to))
def dfs(v, parent, adj, visited, tin, low, timer_ref, bridges):
    visited[v] = True
    tin[v] = low[v] = timer_ref[0]
    timer_ref[0] += 1

    for to in adj[v]:
        if to == parent:
            continue

        if visited[to]:
            # обратное ребро в уже посещённую вершину
            low[v] = min(low[v], tin[to])
        else:
            dfs(to, v, adj, visited, tin, low, timer_ref, bridges)
            low[v] = min(low[v], low[to])

            # условие моста
            if low[to] > tin[v]:
                bridges.append((v, to))


input = sys.stdin.readline

n, m = map(int, input().split())

# граф (как на скриншоте) — просто список соседей
adj = [[] for _ in range(n)]

# номер ребра во входе (1..m) для направления (u->v)
edge_id = {}

# направления (u,v), которые относятся к кратным рёбрам
multiedges = set()

# читаем рёбра
for i in range(1, m + 1):
    u, v = map(int, input().split())
    u -= 1
    v -= 1

    adj[u].append(v)
    adj[v].append(u)

    # если ребро между u и v уже было — это кратность
    if (u, v) in edge_id:
        multiedges.add((u, v))
        multiedges.add((v, u))

    edge_id[(u, v)] = i
    edge_id[(v, u)] = i

visited = [False] * n
tin = [-1] * n
low = [-1] * n
bridges = []
timer_ref = [0]  # чтобы таймер менялся внутри dfs

# граф может быть несвязным: запускаем DFS из каждой непосещённой вершины
for v in range(n):
    if not visited[v]:
        dfs(v, -1, adj, visited, tin, low, timer_ref, bridges)

# переводим мосты (u,v) в номера рёбер, фильтруя кратные
ans = []
for u, v in bridges:
    if (u, v) not in multiedges:
        ans.append(edge_id[(u, v)])

ans.sort()
print(len(ans))
if ans:
    print(*ans)
