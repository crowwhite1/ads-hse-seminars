#include <iostream>
#include <vector>
using namespace std;

int main() {
    int n;
    cin >> n;

    // Решение не оптимально по памяти, так как хранится весь массив значений
    vector<long long> fib(max(2, n + 1), 0);
    fib[0] = 0;
    fib[1] = 1;

    for (int i = 2; i <= n; i++) {
        fib[i] = fib[i - 1] + fib[i - 2];
    }

    cout << fib[n];
    return 0;
}
