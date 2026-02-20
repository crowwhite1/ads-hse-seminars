def dfs_stack(start: int, g: list[list[int]], visited: list[bool]) -> None:
    stack = [start]
    while stack:
        v = stack.pop()
        if visited[v]:
            continue
        visited[v] = True
        # Чтобы порядок обхода был как в рекурсивном DFS (примерно),
        # добавляем соседей в обратном порядке
        for to in reversed(g[v]):
            if not visited[to]:
                stack.append(to)