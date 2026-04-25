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
            return False

        if self.size[a] < self.size[b]:
            a, b = b, a

        self.parent[b] = a
        self.size[a] += self.size[b]
        return True


def kruskal(n, edges):
    """
    n — число вершин
    edges — список рёбер в формате (w, u, v)
    вершины нумеруются от 0 до n - 1
    """
    edges.sort()
    dsu = DSU(n)
    mst_weight = 0

    for w, u, v in edges:
        if dsu.union(u, v):
            mst_weight += w

    return mst_weight
