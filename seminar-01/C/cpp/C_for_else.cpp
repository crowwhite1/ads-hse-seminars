#include <iostream>
using namespace std;

int main() {
    long long n;
    cin >> n;

    bool foundDivisor = false;

    for (long long i = 2; i * i <= n; i++) {
        if (n % i == 0) {
            foundDivisor = true;
            cout << "composite";
            break;
        }
    }

    // Аналог блока else в Python
    if (!foundDivisor) {
        cout << "prime";
    }

    return 0;
}
