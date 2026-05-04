#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n, m;
long long k;

vector<int> bankSizes;
vector<int> head;
vector<int> to;
vector<int> nextEdge;

int edgeCount = 0;

void addEdge(int from, int target) {
    to[edgeCount] = target;
    nextEdge[edgeCount] = head[from];
    head[from] = edgeCount;
    edgeCount++;
}

bool canBuildRoute(int limit) {
    vector<bool> active(n, false);
    int activeCount = 0;

    for (int house = 0; house < n; house++) {
        if (bankSizes[house] <= limit) {
            active[house] = true;
            activeCount++;
        }
    }

    if (activeCount == 0) {
        return false;
    }

    long long movesNeeded = k - 1;

    if (movesNeeded == 0) {
        return true;
    }

    vector<int> indegree(n, 0);

    for (int house = 0; house < n; house++) {
        if (!active[house]) {
            continue;
        }

        for (int edge = head[house]; edge != -1; edge = nextEdge[edge]) {
            int neighbour = to[edge];

            if (active[neighbour]) {
                indegree[neighbour]++;
            }
        }
    }

    vector<int> queue;
    queue.reserve(n);

    for (int house = 0; house < n; house++) {
        if (active[house] && indegree[house] == 0) {
            queue.push_back(house);
        }
    }

    vector<long long> longest(n, 0);
    int processed = 0;

    for (int index = 0; index < (int)queue.size(); index++) {
        int house = queue[index];
        processed++;

        for (int edge = head[house]; edge != -1; edge = nextEdge[edge]) {
            int neighbour = to[edge];

            if (!active[neighbour]) {
                continue;
            }

            if (longest[neighbour] < longest[house] + 1) {
                longest[neighbour] = longest[house] + 1;

                if (longest[neighbour] >= movesNeeded) {
                    return true;
                }
            }

            indegree[neighbour]--;

            if (indegree[neighbour] == 0) {
                queue.push_back(neighbour);
            }
        }
    }

    return processed < activeCount;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> n >> m >> k;

    bankSizes.resize(n);

    for (int i = 0; i < n; i++) {
        cin >> bankSizes[i];
    }

    head.assign(n, -1);
    to.resize(m);
    nextEdge.resize(m);

    for (int i = 0; i < m; i++) {
        int from, target;
        cin >> from >> target;

        from--;
        target--;

        addEdge(from, target);
    }

    vector<int> values = bankSizes;
    sort(values.begin(), values.end());
    values.erase(unique(values.begin(), values.end()), values.end());

    if (!canBuildRoute(values.back())) {
        cout << -1 << '\n';
        return 0;
    }

    int left = 0;
    int right = (int)values.size() - 1;

    while (left < right) {
        int mid = (left + right) / 2;

        if (canBuildRoute(values[mid])) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }

    cout << values[left] << '\n';

    return 0;
}