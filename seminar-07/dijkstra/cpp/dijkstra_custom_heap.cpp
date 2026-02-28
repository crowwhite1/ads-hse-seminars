#include <vector>
#include <limits>
#include <utility>

using namespace std;

using ll = long long;
const ll INF = numeric_limits<ll>::max();

struct MinHeap {
    vector<pair<ll,int>> a;

    bool is_empty() const {
        return a.empty();
    }

    void push(pair<ll,int> x) {
        a.push_back(x);
        shift_up(a.size() - 1);
    }

    pair<ll,int> pop() {
        pair<ll,int> top = a[0];
        pair<ll,int> last = a.back();
        a.pop_back();
        if (!a.empty()) {
            a[0] = last;
            shift_down(0);
        }
        return top;
    }

    void shift_up(int i) {
        while (i > 0) {
            int p = (i - 1) / 2;
            if (a[p].first <= a[i].first) break;
            swap(a[p], a[i]);
            i = p;
        }
    }

    void shift_down(int i) {
        int n = a.size();
        while (true) {
            int l = 2 * i + 1;
            if (l >= n) break;
            int r = l + 1;
            int j = l;
            if (r < n && a[r].first < a[l].first) j = r;
            if (a[i].first <= a[j].first) break;
            swap(a[i], a[j]);
            i = j;
        }
    }
};

vector<ll> dijkstra(const vector<vector<pair<int,ll>>>& adj, int start) {
    int n = adj.size();
    vector<ll> dist(n, INF);
    dist[start] = 0;

    MinHeap heap;
    heap.push({0, start});

    while (!heap.is_empty()) {
        auto [cur_dist, cur] = heap.pop();
        if (cur_dist != dist[cur]) continue;

        for (auto [nxt, w] : adj[cur]) {
            ll nd = cur_dist + w;
            if (nd < dist[nxt]) {
                dist[nxt] = nd;
                heap.push({nd, nxt});
            }
        }
    }

    return dist;
}