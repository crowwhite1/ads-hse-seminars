def dfs(v, graph, color, order):
    color[v] = 1

    for to in graph[v]:
        if color[to] == 0:
            if not dfs(to, graph, color, order):
                return False
        elif color[to] == 1:
            return False

    color[v] = 2
    order.append(v)
    return True


def topological_sort(graph):
    """
    graph — список смежности ориентированного графа
    возвращает топологический порядок вершин
    если в графе есть цикл, возвращает пустой список
    """
    n = len(graph)
    color = [0] * n
    order = []

    for v in range(n):
        if color[v] == 0:
            if not dfs(v, graph, color, order):
                return []

    order.reverse()
    return order
