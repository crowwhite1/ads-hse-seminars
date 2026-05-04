import sys
from collections import deque

input = sys.stdin.readline

NEG_INF = -10**30

n, m = map(int, input().split())

edges = []
graph = [[] for _ in range(n)]
reverse_graph = [[] for _ in range(n)]

for _ in range(m):
    a, b, w = map(int, input().split())
    a -= 1
    b -= 1

    edges.append((a, b, w))
    graph[a].append(b)
    reverse_graph[b].append(a)

dist = [NEG_INF] * n
dist[0] = 0

for _ in range(n - 1):
    changed = False

    for a, b, w in edges:
        if dist[a] != NEG_INF and dist[b] < dist[a] + w:
            dist[b] = dist[a] + w
            changed = True

    if not changed:
        break

if dist[n - 1] == NEG_INF:
    print("Impossible")
    sys.exit()


def bfs(start_vertices, graph):
    used = [False] * n
    q = deque()

    for v in start_vertices:
        used[v] = True
        q.append(v)

    while q:
        v = q.popleft()

        for to in graph[v]:
            if not used[to]:
                used[to] = True
                q.append(to)

    return used


reachable_from_start = bfs([0], graph)
can_reach_finish = bfs([n - 1], reverse_graph)

for a, b, w in edges:
    if dist[a] != NEG_INF and dist[b] < dist[a] + w:
        if reachable_from_start[a] and can_reach_finish[b]:
            print("Infinity")
            sys.exit()

print(dist[n - 1])