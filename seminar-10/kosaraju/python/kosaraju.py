def dfs1(v, graph, used, order):
    used[v] = True
    for to in graph[v]:
        if not used[to]:
            dfs1(to, graph, used, order)
    order.append(v)


def dfs2(v, reversed_graph, used, component, color):
    used[v] = True
    component[v] = color
    for to in reversed_graph[v]:
        if not used[to]:
            dfs2(to, reversed_graph, used, component, color)


def kosaraju(graph):
    """
    graph — список смежности ориентированного графа
    возвращает массив component, где component[v] — номер КСС вершины v
    """
    n = len(graph)
    reversed_graph = [[] for _ in range(n)]

    for v in range(n):
        for to in graph[v]:
            reversed_graph[to].append(v)

    used = [False] * n
    order = []

    for v in range(n):
        if not used[v]:
            dfs1(v, graph, used, order)

    order.reverse()

    used = [False] * n
    component = [-1] * n
    color = 0

    for v in order:
        if not used[v]:
            dfs2(v, reversed_graph, used, component, color)
            color += 1

    return component
