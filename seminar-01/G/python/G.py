import math

n = int(input())
numbers = list(map(int, input().split()))

g = numbers[0]
for x in numbers[1:]:
    g = math.gcd(g, x)

print(g)
