import sys


def bad_triple(a):
    return (
        (a[0] == 1 and a[1] == 3 and a[2] == 2)
        or (a[0] == 3 and a[1] == 1 and a[2] == 2)
        or (a[0] == 3 and a[1] == 2 and a[2] == 1)
    )


def test_case(readline, out):
    n = int(readline())
    a = list(map(int, readline().split()))
    p = [0] * (n + 1)

    for i, v in enumerate(a):
        p[v] = i

    if n == 3 and bad_triple(a):
        out.append("-1")
        return

    half = n >> 1

    ans_x = []
    ans_y = []
    ans_x_append = ans_x.append
    ans_y_append = ans_y.append

    def apply_swap_by_values(v1, v2):
        pos1 = p[v1]
        pos2 = p[v2]

        ans_x_append(pos1 + 1)
        ans_y_append(pos2 + 1)

        a[pos1] = v2
        a[pos2] = v1
        p[v1] = pos2
        p[v2] = pos1

    ind = p[1]

    while ind < n - 1:
        nxt = ind + half
        if nxt < n:
            apply_swap_by_values(1, a[nxt])
            ind = nxt
        else:
            apply_swap_by_values(1, a[n - 1])
            ind = n - 1

    j = 1
    while ind > 0:
        to = p[ind + 1]
        to2 = 0

        while ind > to:
            prev = ind - half
            if prev > to:
                apply_swap_by_values(a[ind], a[prev])
                ind = prev
            else:
                apply_swap_by_values(a[ind], a[to])
                to2 = p[a[ind]]
                ind = to

        limit = n - j - 1
        while ind <= limit:
            if ind < to2:
                if ind == limit:
                    j += 1
                    break

                shift_target = to2 - 1
                if ind != shift_target:
                    apply_swap_by_values(a[ind], a[shift_target])
                    ind = shift_target

                limit = n - j - 1
                nxt = ind + half
                if nxt > limit:
                    target = limit
                    if ind != target:
                        apply_swap_by_values(a[ind], a[target])
                        ind = target
                else:
                    apply_swap_by_values(a[ind], a[nxt])
                    ind = nxt
            else:
                if ind + 1 > n - j:
                    apply_swap_by_values(a[ind], a[to2])
                    ind, to2 = to2, ind
                    apply_swap_by_values(a[ind], a[to2 - 1])
                    ind = n - j - 1
                    j += 1
                    break
                else:
                    apply_swap_by_values(a[ind], a[ind + 1])
                    ind += 1
                    apply_swap_by_values(a[ind], a[to2])
                    ind, to2 = to2, ind

    m = len(ans_x)

    if m == 0:
        out.append("0")
        return

    if m == 1:
        out.append("1")
        out.append(str(ans_x[0]) + " " + str(ans_y[0]))
        return

    res_x = []
    res_y = []
    res_x_append = res_x.append
    res_y_append = res_y.append

    i = 0
    last = m - 1
    while i < last:
        if ans_x[i] == ans_y[i + 1] and ans_y[i] == ans_x[i + 1]:
            i += 2
        else:
            res_x_append(ans_x[i])
            res_y_append(ans_y[i])
            i += 1

    if ans_x[m - 2] != ans_y[m - 1] or ans_y[m - 2] != ans_x[m - 1]:
        res_x_append(ans_x[m - 1])
        res_y_append(ans_y[m - 1])

    sz = len(res_x)
    if sz > 4 * n:
        out.append("-1")
    else:
        out.append(str(sz))
        for i in range(sz):
            out.append(str(res_x[i]) + " " + str(res_y[i]))


def main():
    readline = sys.stdin.buffer.readline
    out = []
    t = int(readline())

    for _ in range(t):
        test_case(readline, out)

    sys.stdout.write("\n".join(out))


if __name__ == "__main__":
    main()