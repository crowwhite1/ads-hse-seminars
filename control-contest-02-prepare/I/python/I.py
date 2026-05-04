import sys

input = sys.stdin.readline


class DSU:
    def __init__(self, size):
        self.parent = list(range(size))
        self.rank = [0] * size

    def find(self, vertex):
        while self.parent[vertex] != vertex:
            self.parent[vertex] = self.parent[self.parent[vertex]]
            vertex = self.parent[vertex]
        return vertex

    def unite(self, first, second):
        first = self.find(first)
        second = self.find(second)

        if first == second:
            return False

        if self.rank[first] < self.rank[second]:
            first, second = second, first

        self.parent[second] = first

        if self.rank[first] == self.rank[second]:
            self.rank[first] += 1

        return True


def building_id(row, col, columns):
    return row * columns + col


rows, columns = map(int, input().split())
city = [list(map(int, input().split())) for _ in range(rows)]

dsu = DSU(rows * columns)

for row in range(rows):
    for col in range(columns):
        value = city[row][col]
        current = building_id(row, col, columns)

        if value & 1 and row + 1 < rows:
            down = building_id(row + 1, col, columns)
            dsu.unite(current, down)

        if value & 2 and col + 1 < columns:
            right = building_id(row, col + 1, columns)
            dsu.unite(current, right)

cables = []

for row in range(rows):
    for col in range(columns):
        current = building_id(row, col, columns)

        if row + 1 < rows and not (city[row][col] & 1):
            down = building_id(row + 1, col, columns)
            cables.append((1, current, down, row + 1, col + 1, 1))

        if col + 1 < columns and not (city[row][col] & 2):
            right = building_id(row, col + 1, columns)
            cables.append((2, current, right, row + 1, col + 1, 2))

cables.sort()

total_cost = 0
added_cables = []

for cost, first, second, row, col, cable_type in cables:
    if dsu.unite(first, second):
        total_cost += cost
        added_cables.append((row, col, cable_type))

print(len(added_cables), total_cost)

for cable in added_cables:
    print(*cable)