package online.ruin_of_future.nameless_chess;

public class Graph extends Matrix {
    // this is an undirected and unweighted graph
    Graph(int point_num) {
        super(point_num, point_num);
    }

    void add_edge(int from, int to) {
        mat[from][to] = mat[to][from] = 1;
    }

    public boolean is_adjacent(int x, int y) {
        return mat[x][y] == 1;
    }
}
