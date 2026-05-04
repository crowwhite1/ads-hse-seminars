import sys
import heapq

input = sys.stdin.readline
INF = 10**18


def dijkstra(n, graph, start, has_mismatch):
    dist = [[INF, INF] for _ in range(n + 1)]

    start_state = 1 if has_mismatch[start] else 0
    dist[start][start_state] = 0

    heap = [(0, start, start_state)]

    while heap:
        current_time, playground, used_mismatch = heapq.heappop(heap)

        if current_time != dist[playground][used_mismatch]:
            continue

        for next_playground, road_time in graph[playground]:
            next_used_mismatch = used_mismatch or has_mismatch[next_playground]
            new_time = current_time + (road_time // 2 if used_mismatch else road_time)

            if new_time < dist[next_playground][next_used_mismatch]:
                dist[next_playground][next_used_mismatch] = new_time
                heapq.heappush(heap, (new_time, next_playground, next_used_mismatch))

    return dist


tests = int(input())

for _ in range(tests):
    n, m, k = map(int, input().split())

    has_mismatch = [False] * (n + 1)

    mismatch_playgrounds = list(map(int, input().split()))
    for playground in mismatch_playgrounds:
        has_mismatch[playground] = True

    graph = [[] for _ in range(n + 1)]

    for _ in range(m):
        first, second, time = map(int, input().split())
        graph[first].append((second, time))
        graph[second].append((first, time))

    madin_dist = dijkstra(n, graph, 1, has_mismatch)
    gosha_dist = dijkstra(n, graph, n, has_mismatch)

    answer = INF

    for playground in range(1, n + 1):
        madin_time = min(madin_dist[playground])
        gosha_time = min(gosha_dist[playground])
        answer = min(answer, max(madin_time, gosha_time))

    print(-1 if answer == INF else answer)