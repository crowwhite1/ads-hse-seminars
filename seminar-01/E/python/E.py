n = int(input())

parts = []

i = 2
while i * i <= n:
    if n % i == 0:
        count = 0
        while n % i == 0:
            n //= i
            count += 1
        if count == 1:
            parts.append(str(i))
        else:
            parts.append(f"{i}^{count}")
    i += 1

if n > 1:
    parts.append(str(n))

print("*".join(parts))