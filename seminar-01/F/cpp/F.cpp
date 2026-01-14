#include <iostream>
#include <vector>
using namespace std;

int main() {
    int k;
    cin >> k;

    int limit = 2000000; // гарантированно хватает для k <= 100000

    vector<bool> is_prime(limit + 1, true);
    is_prime[0] = false;
    is_prime[1] = false;

    for (int p = 2; p * p <= limit; p++) {
        if (is_prime[p]) {
            for (int m = p * p; m <= limit; m += p) {
                is_prime[m] = false;
            }
        }
    }

    int count = 0;
    for (int x = 2; x <= limit; x++) {
        if (is_prime[x]) {
            count++;
            if (count == k) {
                cout << x;
                break;
            }
        }
    }

    return 0;
}
