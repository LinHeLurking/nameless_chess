package online.ruin_of_future.nameless_chess;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board extends Graph {
    private static int TOTAL_CNT = 121;

    private int get_label(Pair<Integer, Integer> pair) {
        return pair.getKey() * 11 + pair.getValue();
    }

    Board() {
        super(TOTAL_CNT);
        for (int i = 2; i < 8; ++i) {
            for (int j = 2; j < 8; ++j) {
                add_edge(get_label(new Pair<>(i, j)), get_label(new Pair<>(i, j + 1)));
                add_edge(get_label(new Pair<>(i, j)), get_label(new Pair<>(i + 1, j)));

            }
        }
        for (int i = 2; i < 8; ++i) {
            add_edge(get_label(new Pair<>(8, i)), get_label(new Pair<>(8, i + 1)));
        }
        for (int j = 2; j < 8; ++j) {
            add_edge(get_label(new Pair<>(8, j)), get_label(new Pair<>(9, j + 1)));
        }
        // link special points manually
        add_edge(get_label(new Pair<>(0, 2)), get_label(new Pair<>(2, 2)));
        add_edge(get_label(new Pair<>(0, 2)), get_label(new Pair<>(0, 4)));
        add_edge(get_label(new Pair<>(0, 4)), get_label(new Pair<>(1, 4)));
        add_edge(get_label(new Pair<>(1, 5)), get_label(new Pair<>(1, 4)));
        add_edge(get_label(new Pair<>(1, 5)), get_label(new Pair<>(1, 6)));
        add_edge(get_label(new Pair<>(0, 6)), get_label(new Pair<>(1, 6)));
        add_edge(get_label(new Pair<>(0, 6)), get_label(new Pair<>(0, 8)));
        add_edge(get_label(new Pair<>(2, 8)), get_label(new Pair<>(0, 8)));
        add_edge(get_label(new Pair<>(2, 8)), get_label(new Pair<>(2, 10)));
        add_edge(get_label(new Pair<>(4, 10)), get_label(new Pair<>(2, 10)));
        add_edge(get_label(new Pair<>(4, 10)), get_label(new Pair<>(4, 9)));
        add_edge(get_label(new Pair<>(5, 9)), get_label(new Pair<>(4, 9)));
        add_edge(get_label(new Pair<>(5, 9)), get_label(new Pair<>(6, 9)));
        add_edge(get_label(new Pair<>(6, 10)), get_label(new Pair<>(6, 9)));
        add_edge(get_label(new Pair<>(6, 10)), get_label(new Pair<>(8, 10)));
        add_edge(get_label(new Pair<>(8, 8)), get_label(new Pair<>(8, 10)));
        add_edge(get_label(new Pair<>(8, 8)), get_label(new Pair<>(10, 8)));
        add_edge(get_label(new Pair<>(10, 6)), get_label(new Pair<>(10, 8)));
        add_edge(get_label(new Pair<>(10, 6)), get_label(new Pair<>(9, 6)));
        add_edge(get_label(new Pair<>(9, 5)), get_label(new Pair<>(9, 6)));
        add_edge(get_label(new Pair<>(9, 5)), get_label(new Pair<>(9, 4)));
        add_edge(get_label(new Pair<>(10, 4)), get_label(new Pair<>(9, 4)));
        add_edge(get_label(new Pair<>(10, 4)), get_label(new Pair<>(10, 2)));
        add_edge(get_label(new Pair<>(8, 2)), get_label(new Pair<>(10, 2)));
        add_edge(get_label(new Pair<>(8, 2)), get_label(new Pair<>(8, 0)));
        add_edge(get_label(new Pair<>(6, 0)), get_label(new Pair<>(8, 0)));
        add_edge(get_label(new Pair<>(6, 0)), get_label(new Pair<>(6, 1)));
        add_edge(get_label(new Pair<>(5, 1)), get_label(new Pair<>(6, 1)));
        add_edge(get_label(new Pair<>(5, 1)), get_label(new Pair<>(4, 1)));
        add_edge(get_label(new Pair<>(4, 0)), get_label(new Pair<>(4, 1)));
        add_edge(get_label(new Pair<>(4, 0)), get_label(new Pair<>(2, 0)));

        // link cross edge manually
        ArrayList<Pair<Integer, Integer>> to_process = new ArrayList<>();
        to_process.add(new Pair<>(3, 5));
        to_process.add(new Pair<>(5, 3));
        to_process.add(new Pair<>(5, 7));
        to_process.add(new Pair<>(7, 5));
        int[] x = {-1, 1};
        int[] y = {-1, 1};
        for (Pair<Integer, Integer> p : to_process) {
            for (int i : x) {
                for (int j : y) {
                    add_edge(get_label(p), get_label(new Pair<>(p.getKey() + i, p.getValue() + j)));
                }
            }
        }
    }

    public Integer[] accessible(int pos) {
        ArrayList<Integer> __ret = new ArrayList<>();
        for (int i = 1; i <= TOTAL_CNT; ++i) {
            if (is_adjacent(pos, i)) {
                __ret.add(i);
            }
        }
        return (Integer[]) __ret.toArray();
    }

    public boolean is_adjacent(int x, int y) {
        if (x < 0 || x >= TOTAL_CNT || y < 0 || y > TOTAL_CNT) {
            return false;
        }
        return super.is_adjacent(x, y);
    }

    public boolean is_adjacent(Pair<Integer, Integer> a, Pair<Integer, Integer> b) {
        return is_adjacent(get_label(a), get_label(b));
    }
}
