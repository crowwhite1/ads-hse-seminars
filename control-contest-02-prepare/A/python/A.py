import sys

input = sys.stdin.readline

n = int(input())

for _ in range(n):
    row = list(map(int, input().split()))
    adjacent = [i + 1 for i, value in enumerate(row) if value == 1]
    print(len(adjacent), *adjacent)