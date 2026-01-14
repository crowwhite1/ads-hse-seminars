#include <iostream>
using namespace std;

int main() {
    int n;
    cin >> n;

    long long a = 0, b = 1;
    for (int i = 0; i < n; i++) {
        long long tmp = a + b;
        a = b;
        b = tmp;
    }

    cout << a;
    return 0;
}
