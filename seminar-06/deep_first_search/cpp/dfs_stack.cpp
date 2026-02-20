#include <vector>
using namespace std;

void dfs_stack(int start, const vector<vector<int>>& g, vector<char>& visited) {
    vector<int> st;
    st.push_back(start);

    while (!st.empty()) {
        int v = st.back();
        st.pop_back();

        if (visited[v]) continue;
        visited[v] = 1;

        // Ближе к рекурсивному порядку: пушим в обратном порядке
        for (int i = (int)g[v].size() - 1; i >= 0; --i) {
            int to = g[v][i];
            if (!visited[to]) st.push_back(to);
        }
    }
}