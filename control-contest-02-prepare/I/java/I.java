import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class I {
    static class DSU {
        int[] parent;
        int[] rank;

        DSU(int size) {
            parent = new int[size];
            rank = new int[size];

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

        boolean unite(int first, int second) {
            first = find(first);
            second = find(second);

            if (first == second) {
                return false;
            }

            if (rank[first] < rank[second]) {
                int temp = first;
                first = second;
                second = temp;
            }

            parent[second] = first;

            if (rank[first] == rank[second]) {
                rank[first]++;
            }

            return true;
        }
    }

    static class Cable implements Comparable<Cable> {
        int cost;
        int firstBuilding;
        int secondBuilding;
        int row;
        int column;
        int type;

        Cable(int cost, int firstBuilding, int secondBuilding, int row, int column, int type) {
            this.cost = cost;
            this.firstBuilding = firstBuilding;
            this.secondBuilding = secondBuilding;
            this.row = row;
            this.column = column;
            this.type = type;
        }

        @Override
        public int compareTo(Cable other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    static int buildingId(int row, int column, int columns) {
        return row * columns + column;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLine = in.readLine().split(" ");
        int rows = Integer.parseInt(firstLine[0]);
        int columns = Integer.parseInt(firstLine[1]);

        int[][] city = new int[rows][columns];

        for (int row = 0; row < rows; row++) {
            String[] parts = in.readLine().split(" ");
            for (int column = 0; column < columns; column++) {
                city[row][column] = Integer.parseInt(parts[column]);
            }
        }

        DSU dsu = new DSU(rows * columns);

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                int value = city[row][column];
                int current = buildingId(row, column, columns);

                if ((value & 1) != 0 && row + 1 < rows) {
                    int down = buildingId(row + 1, column, columns);
                    dsu.unite(current, down);
                }

                if ((value & 2) != 0 && column + 1 < columns) {
                    int right = buildingId(row, column + 1, columns);
                    dsu.unite(current, right);
                }
            }
        }

        ArrayList<Cable> cables = new ArrayList<>();

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                int current = buildingId(row, column, columns);

                if (row + 1 < rows && (city[row][column] & 1) == 0) {
                    int down = buildingId(row + 1, column, columns);
                    cables.add(new Cable(1, current, down, row + 1, column + 1, 1));
                }

                if (column + 1 < columns && (city[row][column] & 2) == 0) {
                    int right = buildingId(row, column + 1, columns);
                    cables.add(new Cable(2, current, right, row + 1, column + 1, 2));
                }
            }
        }

        Collections.sort(cables);

        int totalCost = 0;
        ArrayList<Cable> addedCables = new ArrayList<>();

        for (Cable cable : cables) {
            if (dsu.unite(cable.firstBuilding, cable.secondBuilding)) {
                totalCost += cable.cost;
                addedCables.add(cable);
            }
        }

        StringBuilder output = new StringBuilder();
        output.append(addedCables.size()).append(" ").append(totalCost).append("\n");

        for (Cable cable : addedCables) {
            output.append(cable.row)
                    .append(" ")
                    .append(cable.column)
                    .append(" ")
                    .append(cable.type)
                    .append("\n");
        }

        System.out.print(output);
    }
}