import sys


def test_case():
    data = list(map(int, sys.stdin.read().split()))
    it = iter(data)

    n = int(next(it))
    k = int(next(it))

    a = []
    mx_ans = 0

    for i in range(n):
        x = int(next(it))
        a.append([x, i])   # [value, original_index]
        mx_ans = max(mx_ans, x)

    a.sort()  # sort by value, then by index

    j = 1
    mx = a[0][0]
    sm = 0

    while k and j < n:
        cost = (a[j][0] - a[j - 1][0]) * j
        if cost + sm <= k:
            sm += cost
            mx = a[j][0]
        else:
            break
        j += 1

    for i in range(j):
        a[i][0] = mx

    if j <= n - 1:
        left = 0
        right = k - sm + 1

        while right - left > 1:
            m = (left + right) // 2
            if m * j <= k - sm:
                left = m
            else:
                right = m

        for i in range(j):
            a[i][0] += left

        print(mx_ans - left - mx)

    a.sort(key=lambda x: x[1])  # restore original order

    if n == j:
        print(mx_ans - mx)

    print(*[a[i][0] for i in range(n)])


def main():
    test_case()


if __name__ == "__main__":
    main()