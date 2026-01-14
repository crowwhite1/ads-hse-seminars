n = int(input())

# Решение не оптимально по памяти, так как хранится весь массив значений
fib = [0, 1]

for i in range(2, n + 1):
    fib.append(fib[i - 1] + fib[i - 2])

print(fib[n])
