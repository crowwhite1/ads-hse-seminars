import sys

sys.setrecursionlimit(10**6)


# DFS ищет точки сочленения (cutpoints)
def dfs(v, parent, adj, visited, tin, low, timer_ref, cutpoints):
    visited[v] = True
    tin[v] = low[v] = timer_ref[0]
    timer_ref[0] += 1

    children = 0  # число детей v в DFS-дереве

    for to in adj[v]:
        if to == parent:
            continue

        if visited[to]:
            # обратное ребро
            low[v] = min(low[v], tin[to])
        else:
            dfs(to, v, adj, visited, tin, low, timer_ref, cutpoints)
            children += 1

            low[v] = min(low[v], low[to])

            # v — НЕ корень, и поддерево to не может уйти выше v
            if parent != -1 and low[to] >= tin[v]:
                cutpoints.add(v)

    # отдельное правило для корня DFS
    if parent == -1 and children >= 2:
        cutpoints.add(v)


input = sys.stdin.readline
n, m = map(int, input().split())

adj = [[] for _ in range(n)]
for _ in range(m):
    u, v = map(int, input().split())
    u -= 1
    v -= 1
    adj[u].append(v)
    adj[v].append(u)

visited = [False] * n
tin = [-1] * n
low = [-1] * n
timer_ref = [0]

cutpoints = set()

# граф может быть несвязным
for v in range(n):
    if not visited[v]:
        dfs(v, -1, adj, visited, tin, low, timer_ref, cutpoints)

ans = sorted(x + 1 for x in cutpoints)
print(len(ans))
if ans:
    print(*ans)