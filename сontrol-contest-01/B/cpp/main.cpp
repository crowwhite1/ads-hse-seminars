#include <iostream>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    for (int i = 0; i < 297; i++) {
        cout << "get" << endl;
        int tmp;
        cin >> tmp;
    }

    cout << "put 3" << endl;
    cout << "put 4" << endl;
    cout << "get" << endl;

    int a;
    cin >> a;

    if (a == 3) {
        cout << "queue" << endl;
    } else {
        cout << "stack" << endl;
    }

    return 0;
}