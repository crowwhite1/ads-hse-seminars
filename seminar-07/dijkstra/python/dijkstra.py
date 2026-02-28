import heapq

INF = 10**18


def dijkstra(adj_list, start):
    n = len(adj_list)
    dist = [INF] * n
    dist[start] = 0

    # в куче храним пары (расстояние, вершина)
    heap = [(0, start)]

    while heap:
        cur_dist, cur = heapq.heappop(heap)

        # если запись устарела — пропускаем
        if cur_dist != dist[cur]:
            continue

        for nxt, w in adj_list[cur]:
            nd = cur_dist + w
            if nd < dist[nxt]:
                dist[nxt] = nd
                heapq.heappush(heap, (nd, nxt))

    return dist