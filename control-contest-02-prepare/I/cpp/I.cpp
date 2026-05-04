#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct DSU {
    vector<int> parent;
    vector<int> rank;

    DSU(int size) {
        parent.resize(size);
        rank.assign(size, 0);

        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    int find(int vertex) {
        while (parent[vertex] != vertex) {
            parent[vertex] = parent[parent[vertex]];
            vertex = parent[vertex];
        }
        return vertex;
    }

    bool unite(int first, int second) {
        first = find(first);
        second = find(second);

        if (first == second) {
            return false;
        }

        if (rank[first] < rank[second]) {
            swap(first, second);
        }

        parent[second] = first;

        if (rank[first] == rank[second]) {
            rank[first]++;
        }

        return true;
    }
};

struct Cable {
    int cost;
    int firstBuilding;
    int secondBuilding;
    int row;
    int column;
    int type;

    bool operator<(const Cable& other) const {
        return cost < other.cost;
    }
};

int buildingId(int row, int column, int columns) {
    return row * columns + column;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int rows, columns;
    cin >> rows >> columns;

    vector<vector<int>> city(rows, vector<int>(columns));

    for (int row = 0; row < rows; row++) {
        for (int column = 0; column < columns; column++) {
            cin >> city[row][column];
        }
    }

    DSU dsu(rows * columns);

    for (int row = 0; row < rows; row++) {
        for (int column = 0; column < columns; column++) {
            int value = city[row][column];
            int current = buildingId(row, column, columns);

            if ((value & 1) && row + 1 < rows) {
                int down = buildingId(row + 1, column, columns);
                dsu.unite(current, down);
            }

            if ((value & 2) && column + 1 < columns) {
                int right = buildingId(row, column + 1, columns);
                dsu.unite(current, right);
            }
        }
    }

    vector<Cable> cables;

    for (int row = 0; row < rows; row++) {
        for (int column = 0; column < columns; column++) {
            int current = buildingId(row, column, columns);

            if (row + 1 < rows && !(city[row][column] & 1)) {
                int down = buildingId(row + 1, column, columns);
                cables.push_back({1, current, down, row + 1, column + 1, 1});
            }

            if (column + 1 < columns && !(city[row][column] & 2)) {
                int right = buildingId(row, column + 1, columns);
                cables.push_back({2, current, right, row + 1, column + 1, 2});
            }
        }
    }

    sort(cables.begin(), cables.end());

    int totalCost = 0;
    vector<Cable> addedCables;

    for (const Cable& cable : cables) {
        if (dsu.unite(cable.firstBuilding, cable.secondBuilding)) {
            totalCost += cable.cost;
            addedCables.push_back(cable);
        }
    }

    cout << addedCables.size() << " " << totalCost << "\n";

    for (const Cable& cable : addedCables) {
        cout << cable.row << " " << cable.column << " " << cable.type << "\n";
    }

    return 0;
}