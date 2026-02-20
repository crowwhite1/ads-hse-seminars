from collections import deque

def bfs(start, graph):
    """
    graph — список смежности: graph[v] = список соседей v
    start — стартовая вершина (0-based)
    возвращает массив visited
    """
    n = len(graph)
    visited = [False] * n

    q = deque()
    q.append(start)
    visited[start] = True

    while q:
        v = q.popleft()

        for to in graph[v]:
            if not visited[to]:
                visited[to] = True
                q.append(to)

    return visited