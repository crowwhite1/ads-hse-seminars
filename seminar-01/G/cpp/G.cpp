#include <iostream>
using namespace std;

int gcd(int a, int b) {
    // Алгоритм Евклида
    while (b != 0) {
        int t = a % b;
        a = b;
        b = t;
    }
    return a;
}

int main() {
    int n;
    cin >> n;

    int g, x;
    cin >> g;
    for (int i = 1; i < n; i++) {
        cin >> x;
        g = gcd(g, x);
    }

    cout << g;
    return 0;
}
