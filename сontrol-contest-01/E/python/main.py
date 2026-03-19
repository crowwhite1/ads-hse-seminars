import sys


def swap(arr, i, j):
    arr[i], arr[j] = arr[j], arr[i]


def bad_triple(a):
    return (
        (a[0] == 1 and a[1] == 3 and a[2] == 2)
        or (a[0] == 3 and a[1] == 1 and a[2] == 2)
        or (a[0] == 3 and a[1] == 2 and a[2] == 1)
    )


def test_case(inp, out):
    n = int(inp.readline())
    a = list(map(int, inp.readline().split()))
    p = [0] * (n + 1)

    for i, val in enumerate(a):
        p[val] = i

    if n == 3 and bad_triple(a):
        out.append("-1")
        return

    half = n >> 1
    ans = []

    ind = p[1]

    while ind < n - 1:
        if ind + half < n:
            target = ind + half
            val = a[target]
            ans.append((p[1] + 1, p[val] + 1))
            swap(p, 1, val)
            swap(a, p[1], p[val])
            ind += half
        else:
            target = n - 1
            val = a[target]
            ans.append((p[1] + 1, p[val] + 1))
            swap(p, 1, val)
            swap(a, p[1], p[val])
            ind = n - 1

    j = 1
    while ind > 0:
        to = p[ind + 1]
        to2 = 0

        while ind > to:
            if ind - half > to:
                v1 = a[ind]
                v2 = a[ind - half]
                ans.append((p[v1] + 1, p[v2] + 1))
                swap(p, v1, v2)
                swap(a, p[v1], p[v2])
                ind -= half
            else:
                target = to
                v1 = a[ind]
                v2 = a[target]
                ans.append((p[v1] + 1, p[v2] + 1))
                swap(p, v1, v2)
                swap(a, p[v1], p[v2])
                to2 = p[a[ind]]
                ind = to

        while ind <= n - j - 1:
            if ind < to2:
                if ind == n - j - 1:
                    j += 1
                    break

                shift_target = ind + (to2 - ind) - 1

                if p[a[ind]] != p[a[shift_target]]:
                    v1 = a[ind]
                    v2 = a[shift_target]
                    ans.append((p[v1] + 1, p[v2] + 1))
                    swap(p, v1, v2)
                    swap(a, p[v1], p[v2])
                    ind = shift_target

                if ind + half > n - j - 1:
                    target = n - j - 1
                    if p[a[ind]] != p[a[target]]:
                        v1 = a[ind]
                        v2 = a[target]
                        ans.append((p[v1] + 1, p[v2] + 1))
                        swap(p, v1, v2)
                        swap(a, p[v1], p[v2])
                        ind = target
                else:
                    target = ind + half
                    v1 = a[ind]
                    v2 = a[target]
                    ans.append((p[v1] + 1, p[v2] + 1))
                    swap(p, v1, v2)
                    swap(a, p[v1], p[v2])
                    ind += half
            else:
                if ind + 1 > n - j:
                    v1 = a[ind]
                    v2 = a[to2]
                    ans.append((p[v1] + 1, p[v2] + 1))
                    swap(p, v1, v2)
                    swap(a, p[v1], p[v2])

                    tmp = ind
                    ind = to2
                    to2 = tmp

                    v1 = a[ind]
                    v2 = a[to2 - 1]
                    ans.append((p[v1] + 1, p[v2] + 1))
                    swap(p, v1, v2)
                    swap(a, p[v1], p[v2])

                    ind = n - j - 1
                    j += 1
                    break
                else:
                    v1 = a[ind]
                    v2 = a[ind + 1]
                    ans.append((p[v1] + 1, p[v2] + 1))
                    swap(p, v1, v2)
                    swap(a, p[v1], p[v2])

                    ind += 1

                    v1 = a[ind]
                    v2 = a[to2]
                    ans.append((p[v1] + 1, p[v2] + 1))
                    swap(p, v1, v2)
                    swap(a, p[v1], p[v2])

                    tmp = ind
                    ind = to2
                    to2 = tmp

    m = len(ans)

    if m == 0:
        out.append("0")
        return

    if m == 1:
        out.append("1")
        out.append(f"{ans[0][0]} {ans[0][1]}")
        return

    res = []
    i = 0
    while i < m - 1:
        if ans[i][0] == ans[i + 1][1] and ans[i][1] == ans[i + 1][0]:
            i += 2
        else:
            res.append(ans[i])
            i += 1

    if ans[m - 2][0] != ans[m - 1][1] or ans[m - 2][1] != ans[m - 1][0]:
        res.append(ans[m - 1])

    if len(res) > 4 * n:
        out.append("-1")
    else:
        out.append(str(len(res)))
        for x, y in res:
            out.append(f"{x} {y}")


def main():
    inp = sys.stdin
    out = []

    t = int(inp.readline())
    for _ in range(t):
        test_case(inp, out)

    sys.stdout.write("\n".join(out))


if __name__ == "__main__":
    main()