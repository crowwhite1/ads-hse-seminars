#include <vector>
#include <queue>
#include <limits>

using namespace std;

using ll = long long;
const ll INF = numeric_limits<ll>::max();

vector<ll> dijkstra(const vector<vector<pair<int, ll>>>& adj, int start) {
    int n = adj.size();
    vector<ll> dist(n, INF);
    dist[start] = 0;

    priority_queue<
        pair<ll,int>,
        vector<pair<ll,int>>,
        greater<pair<ll,int>>
    > pq;

    pq.push({0, start});

    while (!pq.empty()) {
        auto [cur_dist, cur] = pq.top();
        pq.pop();

        if (cur_dist != dist[cur]) continue;

        for (auto [nxt, w] : adj[cur]) {
            ll nd = cur_dist + w;
            if (nd < dist[nxt]) {
                dist[nxt] = nd;
                pq.push({nd, nxt});
            }
        }
    }

    return dist;
}