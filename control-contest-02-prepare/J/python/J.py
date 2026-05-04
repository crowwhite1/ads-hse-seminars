import sys

input = sys.stdin.buffer.readline


def has_route_with_limit(limit):
    active_mask = 0

    for house in range(n):
        if bank_sizes[house] <= limit:
            active_mask |= 1 << house

    if active_mask == 0:
        return False

    if moves_needed == 0:
        return True

    indegree = [0] * n

    mask = active_mask
    while mask:
        bit = mask & -mask
        house = bit.bit_length() - 1
        mask -= bit

        neighbours = graph[house] & active_mask
        while neighbours:
            to_bit = neighbours & -neighbours
            to = to_bit.bit_length() - 1
            neighbours -= to_bit
            indegree[to] += 1

    queue = []
    mask = active_mask
    active_count = 0

    while mask:
        bit = mask & -mask
        house = bit.bit_length() - 1
        mask -= bit

        active_count += 1
        if indegree[house] == 0:
            queue.append(house)

    longest = [0] * n
    processed = 0
    head = 0

    while head < len(queue):
        house = queue[head]
        head += 1
        processed += 1

        neighbours = graph[house] & active_mask
        while neighbours:
            to_bit = neighbours & -neighbours
            to = to_bit.bit_length() - 1
            neighbours -= to_bit

            if longest[to] < longest[house] + 1:
                longest[to] = longest[house] + 1

                if longest[to] >= moves_needed:
                    return True

            indegree[to] -= 1
            if indegree[to] == 0:
                queue.append(to)

    return processed < active_count


n, m, k = map(int, input().split())
bank_sizes = list(map(int, input().split()))

graph = [0] * n

for _ in range(m):
    u, v = map(int, input().split())
    u -= 1
    v -= 1
    graph[u] |= 1 << v

moves_needed = k - 1

values = sorted(set(bank_sizes))

if not has_route_with_limit(values[-1]):
    print(-1)
else:
    left = 0
    right = len(values) - 1

    while left < right:
        mid = (left + right) // 2

        if has_route_with_limit(values[mid]):
            right = mid
        else:
            left = mid + 1

    print(values[left])