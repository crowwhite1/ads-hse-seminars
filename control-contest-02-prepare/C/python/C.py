from collections import deque
import sys

input = sys.stdin.readline

n = int(input())
a = list(map(int, input().split()))

reverse_graph = [[] for _ in range(n)]

for i in range(n):
    left = i - a[i]
    right = i + a[i]

    if left >= 0:
        reverse_graph[left].append(i)

    if right < n:
        reverse_graph[right].append(i)


def bfs(parity):
    dist = [-1] * n
    q = deque()

    for i in range(n):
        if a[i] % 2 == parity:
            dist[i] = 0
            q.append(i)

    while q:
        v = q.popleft()

        for to in reverse_graph[v]:
            if dist[to] == -1:
                dist[to] = dist[v] + 1
                q.append(to)

    return dist


dist_even = bfs(0)
dist_odd = bfs(1)

answer = []

for i in range(n):
    if a[i] % 2 == 0:
        answer.append(dist_odd[i])
    else:
        answer.append(dist_even[i])

print(*answer)