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

    def is_same(self, a, b):
        return self.get(a) == self.get(b)

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

    def component_size(self, x):
        return self.size[self.get(x)]
