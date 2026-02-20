def dfs_recursive(v: int, g: list[list[int]], visited: list[bool]) -> None:
    visited[v] = True
    for to in g[v]:
        if not visited[to]:
            dfs_recursive(to, g, visited)