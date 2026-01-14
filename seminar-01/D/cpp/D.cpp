#include <iostream>
using namespace std;

int main() {
    long long a, b, c;
    cin >> a >> b >> c;

    // Не получится решать перебором, так как в условии числа 10^15, поэтому решаем через формулу
    // (2*a + 3*b + 4*c + 5*d)/(a + b + c + d) >= 3.5
    long long need = 3 * a + b - c;

    if (need <= 0) {
        cout << 0;
    } else {
        cout << (need + 2) / 3; // ceil(need / 3)
    }

    return 0;
}
