def gcd(a, b):
    # Алгоритм Евклида
    while b != 0:
        a, b = b, a % b
    return a


n = int(input())
numbers = list(map(int, input().split()))

g = numbers[0]
for x in numbers[1:]:
    g = gcd(g, x)

print(g)
