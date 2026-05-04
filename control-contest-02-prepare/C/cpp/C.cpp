#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int n;
vector<int> a;
vector<vector<int>> reverseGraph;

vector<int> bfs(int parity) {
    vector<int> dist(n, -1);
    queue<int> q;

    for (int i = 0; i < n; i++) {
        if (a[i] % 2 == parity) {
            dist[i] = 0;
            q.push(i);
        }
    }

    while (!q.empty()) {
        int v = q.front();
        q.pop();

        for (int to : reverseGraph[v]) {
            if (dist[to] == -1) {
                dist[to] = dist[v] + 1;
                q.push(to);
            }
        }
    }

    return dist;
}

int main() {
    cin >> n;

    a.resize(n);
    reverseGraph.resize(n);

    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    for (int i = 0; i < n; i++) {
        int left = i - a[i];
        int right = i + a[i];

        if (left >= 0) {
            reverseGraph[left].push_back(i);
        }

        if (right < n) {
            reverseGraph[right].push_back(i);
        }
    }

    vector<int> distEven = bfs(0);
    vector<int> distOdd = bfs(1);

    for (int i = 0; i < n; i++) {
        if (a[i] % 2 == 0) {
            cout << distOdd[i];
        } else {
            cout << distEven[i];
        }

        if (i + 1 < n) {
            cout << " ";
        }
    }

    cout << "\n";

    return 0;
}