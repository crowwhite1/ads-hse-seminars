import sys

sys.setrecursionlimit(10**6)


def dfs(v, parent, adj, visited, tin, low, timer_ref, bridges):
    visited[v] = True
    tin[v] = low[v] = timer_ref[0]
    timer_ref[0] += 1

    for to in adj[v]:
        if to == parent:
            continue

        if visited[to]:
            low[v] = min(low[v], tin[to])
        else:
            dfs(to, v, adj, visited, tin, low, timer_ref, bridges)
            low[v] = min(low[v], low[to])

            if low[to] > tin[v]:
                bridges.append((v, to))


def find_bridges(n, adj):
    visited = [False] * n
    tin = [-1] * n
    low = [-1] * n
    timer_ref = [0]
    bridges = []

    for v in range(n):
        if not visited[v]:
            dfs(v, -1, adj, visited, tin, low, timer_ref, bridges)

    return bridges


def main():
    input = sys.stdin.readline
    n, m = map(int, input().split())

    adj = [[] for _ in range(n)]
    edges = []

    for _ in range(m):
        u, v = map(int, input().split())
        u -= 1
        v -= 1
        adj[u].append(v)
        adj[v].append(u)
        edges.append((u, v))

    bridges = find_bridges(n, adj)

    print(len(bridges))
    for u, v in bridges:
        print(u + 1, v + 1)


if __name__ == "__main__":
    main()