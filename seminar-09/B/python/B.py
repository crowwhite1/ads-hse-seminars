class DSU:
    def __init__(self, size):
        self.parent = [i for i in range(size)]
        self.size = [1 for _ in range(size)]

    def get(self, x):
        if self.parent[x] == x:
            return x
        leader = self.get(self.parent[x])
        self.parent[x] = leader
        return leader

    def union(self, a, b):
        a = self.get(a)
        b = self.get(b)

        if a == b:
            return

        if self.size[a] < self.size[b]:
            a, b = b, a

        self.parent[b] = a
        self.size[a] += self.size[b]

    def is_same(self, x, y):
        return self.get(x) == self.get(y)


n, m = map(int, input().split())
edges = []
for _ in range(m):
    fr, to, w = map(int, input().split())
    fr -= 1
    to -= 1
    edges.append((w, fr, to))
edges.sort()
dsu = DSU(n)
mst_weight = 0
for (w, fr, to) in edges:
    if not dsu.is_same(fr, to):
        dsu.union(fr, to)
        mst_weight += w
print(mst_weight)