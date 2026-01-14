k = int(input())

limit = 2_000_000  # гарантированно хватает для k <= 100000

is_prime = [True] * (limit + 1)
is_prime[0] = is_prime[1] = False

for p in range(2, int(limit ** 0.5) + 1):
    if is_prime[p]:
        for m in range(p * p, limit + 1, p):
            is_prime[m] = False

count = 0
for x in range(2, limit + 1):
    if is_prime[x]:
        count += 1
        if count == k:
            print(x)
            break
