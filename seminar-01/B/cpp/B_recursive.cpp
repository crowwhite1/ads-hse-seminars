#include <iostream>
#include <vector>
using namespace std;

long long fibonacci(int n, vector<long long>& memo) {
    // Без memo рекурсивное решение работает слишком медленно
    if (memo[n] != -1) return memo[n];
    if (n == 0) return memo[n] = 0;
    if (n == 1) return memo[n] = 1;
    return memo[n] = fibonacci(n - 1, memo) + fibonacci(n - 2, memo);
}

int main() {
    int n;
    cin >> n;

    vector<long long> memo(n + 1, -1);
    cout << fibonacci(n, memo);

    return 0;
}
