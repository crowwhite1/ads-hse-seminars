n = int(input())
arr = [list(map(int, input().split())) for i in range(n)]
for i in range(n):
    count = arr[i].count(1)
    if count >= 1:
        print(count, end=" ")
        s = []
        for j in range(n):
            if arr[i][j] == 1:
                s.append(j+1)
        print(*s)
    else:
        print(0)