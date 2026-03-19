import sys

def test_case():
    n, k = map(int, sys.stdin.readline().split())
    a = list(map(int, sys.stdin.readline().split()))

    a.sort()

    mx = max(abs(a[0]), a[-1])
    ans = 0

    for i in range(n):
        if k > 0:
            ans += mx
            k -= 1
        else:
            ans += a[i]

    print(ans)

def main():
    test_case()

if __name__ == "__main__":
    main()