from itertools import count


def dfs(v, arr, visited):
    visited[v] = True
    for to in arr[v]:
        if not visited[to]:
            dfs(to, arr, visited)

n, q = map(int, input().split())
arr = [[] for i in range(n)]
for i in range(n):
    row = list(map(int, input().split()))
    for j in range(n):
        if row[j] == 1:
            arr[i].append(j)

visited = [False] * n
dfs(q-1, arr, visited)
print(visited.count(True))