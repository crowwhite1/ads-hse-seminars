#include <bits/stdc++.h>
using namespace std;

using ll = long long;

const ll INF = 4e18;

struct Edge {
    int to;
    int weight;
};

struct Road {
    int u;
    int v;
    int w;
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m, s;
    cin >> n >> m >> s;

    vector<vector<Edge>> graph(n + 1);
    vector<Road> roads;
    roads.reserve(m);

    for (int i = 0; i < m; i++) {
        int u, v, w;
        cin >> u >> v >> w;

        graph[u].push_back({v, w});
        graph[v].push_back({u, w});

        roads.push_back({u, v, w});
    }

    int length;
    cin >> length;

    vector<ll> dist(n + 1, INF);
    dist[s] = 0;

    priority_queue<pair<ll, int>, vector<pair<ll, int>>, greater<pair<ll, int>>> pq;
    pq.push({0, s});

    while (!pq.empty()) {
        auto [curDist, v] = pq.top();
        pq.pop();

        if (curDist != dist[v]) {
            continue;
        }

        for (Edge edge : graph[v]) {
            int to = edge.to;
            int weight = edge.weight;

            if (dist[to] > curDist + weight) {
                dist[to] = curDist + weight;
                pq.push({dist[to], to});
            }
        }
    }

    int answer = 0;

    for (int i = 1; i <= n; i++) {
        if (dist[i] == length) {
            answer++;
        }
    }

    for (Road road : roads) {
        int u = road.u;
        int v = road.v;
        int w = road.w;

        ll du = dist[u];
        ll dv = dist[v];

        if (du < length && dv < length) {
            ll total = du + dv + w;

            if (total == 2LL * length) {
                answer++;
            } else if (total > 2LL * length) {
                answer += 2;
            }

        } else if (du < length && length < dv) {
            answer++;

        } else if (dv < length && length < du) {
            answer++;

        } else if (du == length && dv < length && dv + w > du) {
            answer++;

        } else if (dv == length && du < length && du + w > dv) {
            answer++;
        }
    }

    cout << answer << "\n";

    return 0;
}