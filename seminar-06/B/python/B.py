def dfs(v, arr, visited):
    visited[v] = True
    for to in arr[v]:
        if not visited[to]:
            dfs(to, arr, visited)

n = int(input())
arr = [[] for i in range(n)]
edges = 0
for i in range(n):
    row = list(map(int, input().split()))
    for j in range(n):
        if row[j] == 1:
            arr[i].append(j)
            if j>i:
                edges += 1

visited = [False] * n
dfs(0, arr, visited)
print("YES" if edges == n - 1 and all(visited) else "NO")