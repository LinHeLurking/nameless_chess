package online.ruin_of_future.nameless_chess;

public class Matrix {
    public int[][] mat;

    Matrix(int row, int col, boolean id) {
        mat = new int[row][col];
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                mat[i][j] = i != j ? 0 : id ? 1 : 0;
            }
        }
    }

    Matrix(int row, int col) {
        this(row, col, false);
    }
}
