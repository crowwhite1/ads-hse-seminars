#include <iostream>
using namespace std;

int main() {
    int n;
    cin >> n;

    long long a = 1;
    long long b = 1;
    long long current = 0;

    for (int i = 0; i < n; i++) {
        long long aVal = a * a;
        long long bVal = b * b * b;

        if (aVal < bVal) {
            current = aVal;
            a++;
        } else if (aVal > bVal) {
            current = bVal;
            b++;
        } else {
            current = aVal;
            a++;
            b++;
        }
    }

    cout << current;
    return 0;
}
