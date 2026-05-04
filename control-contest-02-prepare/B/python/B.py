import sys

input = sys.stdin.readline


class DSU:
    def __init__(self, n):
        self.parent = list(range(n))
        self.size = [1] * n

    def get(self, v):
        while self.parent[v] != v:
            self.parent[v] = self.parent[self.parent[v]]
            v = self.parent[v]
        return v

    def union(self, a, b):
        a = self.get(a)
        b = self.get(b)

        if a == b:
            return False

        if self.size[a] < self.size[b]:
            a, b = b, a

        self.parent[b] = a
        self.size[a] += self.size[b]
        return True


n = int(input())

weights = [list(map(int, input().split())) for _ in range(n)]

dsu = DSU(n)
edges = []

for i in range(n):
    row = list(map(int, input().split()))

    for j in range(i + 1, n):
        if row[j] == 1:
            dsu.union(i, j)
        else:
            edges.append((weights[i][j], i, j))

edges.sort()

answer = 0

for w, u, v in edges:
    if dsu.union(u, v):
        answer += w

print(answer)