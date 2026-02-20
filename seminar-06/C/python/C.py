n = int(input())
sinks = []
is_source = [True] * n

for i in range(n):
    row = list(map(int, input().split()))

    if not any(row):          # нет исходящих рёбер → сток
        sinks.append(i + 1)

    for j in range(n):        # есть входящее ребро → не источник
        if row[j]:
            is_source[j] = False

sources = [i + 1 for i in range(n) if is_source[i]]

print(len(sources))
if len(sources) > 0:
    print(*sources, sep='\n')
print(len(sinks))
if len(sinks) > 0:
    print(*sinks, sep='\n')
