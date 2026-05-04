#include <iostream>
#include <vector>
#include <queue>
#include <array>
#include <algorithm>

using namespace std;

using ll = long long;

const ll INF = 4e18;

struct Road {
    int to;
    int time;
};

struct State {
    ll time;
    int playground;
    int usedMismatch;

    bool operator>(const State& other) const {
        return time > other.time;
    }
};

vector<array<ll, 2>> dijkstra(
    int n,
    const vector<vector<Road>>& graph,
    int start,
    const vector<bool>& hasMismatch
) {
    vector<array<ll, 2>> dist(n + 1, {INF, INF});

    int startState = hasMismatch[start] ? 1 : 0;
    dist[start][startState] = 0;

    priority_queue<State, vector<State>, greater<State>> queue;
    queue.push({0, start, startState});

    while (!queue.empty()) {
        State current = queue.top();
        queue.pop();

        if (current.time != dist[current.playground][current.usedMismatch]) {
            continue;
        }

        for (const Road& road : graph[current.playground]) {
            int nextUsedMismatch = (current.usedMismatch || hasMismatch[road.to]) ? 1 : 0;
            ll newTime = current.time + (current.usedMismatch ? road.time / 2 : road.time);

            if (newTime < dist[road.to][nextUsedMismatch]) {
                dist[road.to][nextUsedMismatch] = newTime;
                queue.push({newTime, road.to, nextUsedMismatch});
            }
        }
    }

    return dist;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int tests;
    cin >> tests;

    while (tests--) {
        int n, m, k;
        cin >> n >> m >> k;

        vector<bool> hasMismatch(n + 1, false);

        for (int i = 0; i < k; i++) {
            int playground;
            cin >> playground;
            hasMismatch[playground] = true;
        }

        vector<vector<Road>> graph(n + 1);

        for (int i = 0; i < m; i++) {
            int firstPlayground, secondPlayground, time;
            cin >> firstPlayground >> secondPlayground >> time;

            graph[firstPlayground].push_back({secondPlayground, time});
            graph[secondPlayground].push_back({firstPlayground, time});
        }

        vector<array<ll, 2>> madinDist = dijkstra(n, graph, 1, hasMismatch);
        vector<array<ll, 2>> goshaDist = dijkstra(n, graph, n, hasMismatch);

        ll answer = INF;

        for (int playground = 1; playground <= n; playground++) {
            ll madinTime = min(madinDist[playground][0], madinDist[playground][1]);
            ll goshaTime = min(goshaDist[playground][0], goshaDist[playground][1]);

            answer = min(answer, max(madinTime, goshaTime));
        }

        cout << (answer == INF ? -1 : answer) << '\n';
    }

    return 0;
}