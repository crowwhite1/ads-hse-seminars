from functools import lru_cache

n = int(input())


@lru_cache()  # Без lru_cache рекурсивное решение работает слишком медленно
def fibonacci(k):
    if k == 0:
        return 0
    if k == 1:
        return 1
    return fibonacci(k - 1) + fibonacci(k - 2)


print(fibonacci(n))
