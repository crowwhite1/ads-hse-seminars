import sys

sys.setrecursionlimit(10**6)


def dfs(v, parent, adj, visited, tin, low, timer_ref, cutpoints):
    visited[v] = True
    tin[v] = low[v] = timer_ref[0]
    timer_ref[0] += 1

    children = 0

    for to in adj[v]:
        if to == parent:
            continue

        if visited[to]:
            low[v] = min(low[v], tin[to])
        else:
            dfs(to, v, adj, visited, tin, low, timer_ref, cutpoints)
            low[v] = min(low[v], low[to])
            children += 1

            if parent != -1 and low[to] >= tin[v]:
                cutpoints.add(v)

    if parent == -1 and children >= 2:
        cutpoints.add(v)


def find_cutpoints(n, adj):
    visited = [False] * n
    tin = [-1] * n
    low = [-1] * n
    timer_ref = [0]
    cutpoints = set()

    for v in range(n):
        if not visited[v]:
            dfs(v, -1, adj, visited, tin, low, timer_ref, cutpoints)

    return sorted(cutpoints)


def main():
    input = sys.stdin.readline
    n, m = map(int, input().split())

    adj = [[] for _ in range(n)]

    for _ in range(m):
        u, v = map(int, input().split())
        u -= 1
        v -= 1
        adj[u].append(v)
        adj[v].append(u)

    cutpoints = find_cutpoints(n, adj)

    print(len(cutpoints))
    for v in cutpoints:
        print(v + 1)


if __name__ == "__main__":
    main()