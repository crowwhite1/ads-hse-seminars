INF = 10**18

class MinHeap:
    def __init__(self, data=None):
        self.a = []
        if data is not None:
            self.a = list(data)
            self.heapify()

    def __len__(self):
        return len(self.a)

    def is_empty(self):
        return len(self.a) == 0

    def peek(self):
        if not self.a:
            return None
        return self.a[0]

    def push(self, x):
        self.a.append(x)
        self.shift_up(len(self.a) - 1)

    def pop(self):
        if not self.a:
            return None
        top = self.a[0]
        last = self.a.pop()
        if self.a:
            self.a[0] = last
            self.shift_down(0)
        return top

    def shift_up(self, i):
        a = self.a
        while i > 0:
            p = (i - 1) // 2
            if a[p] <= a[i]:
                break
            a[p], a[i] = a[i], a[p]
            i = p

    def shift_down(self, i, n=None):
        a = self.a
        if n is None:
            n = len(a)
        while True:
            l = 2 * i + 1
            if l >= n:
                break
            r = l + 1
            j = l
            if r < n and a[r] < a[l]:
                j = r
            if a[i] <= a[j]:
                break
            a[i], a[j] = a[j], a[i]
            i = j

    def heapify(self):
        n = len(self.a)
        for i in range(n // 2 - 1, -1, -1):
            self.shift_down(i, n)


def dijkstra(adj_list, start):
    n = len(adj_list)
    dist = [INF] * n
    dist[start] = 0

    q = MinHeap()
    q.push((0, start))  # (расстояние, вершина)

    while not q.is_empty():
        cur_dist, cur = q.pop()

        # устаревшая запись (в кучу могли положить более плохое расстояние ранее)
        if cur_dist != dist[cur]:
            continue

        for nxt, w in adj_list[cur]:
            nd = cur_dist + w
            if nd < dist[nxt]:
                dist[nxt] = nd
                q.push((nd, nxt))

    return dist

n, m = map(int, input().split())
adj_list = [[] for _ in range(n)]
for _ in range(m):
    fr, to, w = map(int, input().split())
    adj_list[fr].append((to, w))
dist = dijkstra(adj_list, 0)
print(*dist[1:], sep="\n")