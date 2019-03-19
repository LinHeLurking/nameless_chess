package online.ruin_of_future.nameless_chess;

import java.util.ArrayList;

public class Board extends Graph {
	private int INNER_CNT = 49;
	private int TOTAL_CNT = 77;
	
	Board() {
		super(80);
		for (int i = 1; i <= INNER_CNT; ++i) {
			int j;
			if ((j = i + 1) >= 1 && j <= INNER_CNT) addedge(i, j);
			if ((j = i - 1) >= 1 && j <= INNER_CNT) addedge(i, j);
			if ((j = i + 7) >= 1 && j <= INNER_CNT) addedge(i, j);
			if ((j = i - 7) >= 1 && j <= INNER_CNT) addedge(i, j);
		}
		int[] tmp_pos = {11, 23, 27, 39};
		int[] offset = {-6, -8, 6, 8};
		for (int i : tmp_pos) {
			for (int j : offset) {
				addedge(i, i + j);
			}
		}
		addedge(1, 50);
		addedge(51, 50);
		addedge(51, 52);
		addedge(53, 52);
		addedge(53, 4);
		addedge(53, 54);
		addedge(55, 54);
		addedge(55, 56);
		addedge(7, 56);
		addedge(7, 57);
		addedge(58, 57);
		addedge(58, 59);
		addedge(60, 59);
		addedge(60, 28);
		addedge(60, 61);
		addedge(62, 61);
		addedge(62, 63);
		addedge(49, 63);
		addedge(49, 64);
		addedge(65, 64);
		addedge(66, 64);
		addedge(66, 67);
		addedge(46, 67);
		addedge(68, 67);
		addedge(68, 69);
		addedge(70, 69);
		addedge(70, 43);
		addedge(71, 43);
		addedge(71, 72);
		addedge(73, 72);
		addedge(73, 74);
		addedge(22, 74);
		addedge(75, 74);
		addedge(75, 76);
		addedge(77, 76);
		addedge(77, 1);
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
		if (x <= 0 || x > TOTAL_CNT || y <= 0 || y >= TOTAL_CNT) {
			return false;
		}
		return super.is_adjacent(x, y);
	}
}
