import sys
from collections import deque

input = sys.stdin.readline


def bfs_path(graph, start, finish):
    n = len(graph)

    dist = [-1] * n
    parent = [-1] * n

    q = deque([start])
    dist[start] = 0

    while q:
        v = q.popleft()

        if v == finish:
            break

        for to in range(n):
            if graph[v][to] == 1 and dist[to] == -1:
                dist[to] = dist[v] + 1
                parent[to] = v
                q.append(to)

    if dist[finish] == -1:
        return -1, []

    path = []
    cur = finish

    while cur != -1:
        path.append(cur + 1)
        cur = parent[cur]

    path.reverse()
    return dist[finish], path


n = int(input())

graph = [list(map(int, input().split())) for _ in range(n)]

start, finish = map(int, input().split())
start -= 1
finish -= 1

distance, path = bfs_path(graph, start, finish)

if distance == -1:
    print(-1)
else:
    print(distance)
    if distance > 0:
        print(*path)