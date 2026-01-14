#include <iostream>
#include <vector>
#include <string>
using namespace std;

int main() {
    long long n;
    cin >> n;

    vector<string> parts;

    for (long long i = 2; i * i <= n; i++) {
        if (n % i == 0) {
            int count = 0;
            while (n % i == 0) {
                n /= i;
                count++;
            }
            if (count == 1) {
                parts.push_back(to_string(i));
            } else {
                parts.push_back(to_string(i) + "^" + to_string(count));
            }
        }
    }

    if (n > 1) {
        parts.push_back(to_string(n));
    }

    for (size_t i = 0; i < parts.size(); i++) {
        if (i > 0) cout << "*";
        cout << parts[i];
    }

    return 0;
}
