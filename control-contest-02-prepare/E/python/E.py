import sys
import heapq

INF = 10**18

data = list(map(int, sys.stdin.buffer.read().split()))
idx = 0

n = data[idx]
m = data[idx + 1]
s = data[idx + 2]
idx += 3

graph = [[] for _ in range(n + 1)]
edges = []

for _ in range(m):
    u = data[idx]
    v = data[idx + 1]
    w = data[idx + 2]
    idx += 3

    graph[u].append((v, w))
    graph[v].append((u, w))
    edges.append((u, v, w))

length = data[idx]

dist = [INF] * (n + 1)
dist[s] = 0

heap = [(0, s)]

while heap:
    cur_dist, v = heapq.heappop(heap)

    if cur_dist != dist[v]:
        continue

    for to, weight in graph[v]:
        new_dist = cur_dist + weight

        if new_dist < dist[to]:
            dist[to] = new_dist
            heapq.heappush(heap, (new_dist, to))

answer = 0

for v in range(1, n + 1):
    if dist[v] == length:
        answer += 1

for u, v, w in edges:
    du = dist[u]
    dv = dist[v]

    if du < length and dv < length:
        total = du + dv + w

        if total == 2 * length:
            answer += 1
        elif total > 2 * length:
            answer += 2

    elif du < length < dv:
        answer += 1

    elif dv < length < du:
        answer += 1

    elif du == length and dv < length and dv + w > du:
        answer += 1

    elif dv == length and du < length and du + w > dv:
        answer += 1

print(answer)