#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;

    vector<char> isSource(n, 1); // сначала считаем, что все истоки
    vector<int> sinks;
    sinks.reserve(n);

    for (int i = 0; i < n; i++) {
        bool hasOut = false; // есть ли исходящее ребро из i

        for (int j = 0; j < n; j++) {
            int x;
            cin >> x;
            if (x == 1) {
                hasOut = true;   // из i есть ребро
                isSource[j] = 0; // в j входит ребро => j не исток
            }
        }

        if (!hasOut) sinks.push_back(i + 1); // сток (нумерация с 1)
    }

    vector<int> sources;
    sources.reserve(n);
    for (int i = 0; i < n; i++) {
        if (isSource[i]) sources.push_back(i + 1);
    }

    // Вывод: как в Python — сначала количество, потом вершины по одной в строке
    cout << sources.size() << "\n";
    for (int v : sources) cout << v << "\n";

    cout << sinks.size() << "\n";
    for (int v : sinks) cout << v << "\n";

    return 0;
}