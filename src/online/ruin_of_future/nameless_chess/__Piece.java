package online.ruin_of_future.nameless_chess;

public class __Piece {
    int owner;
    int index;
    int label_in_board;

    __Piece(int owner, int index, int label_in_board) {
        this.owner = owner;
        this.index = index;
        this.label_in_board = label_in_board;
    }

    void moveto(int destination) throws MoveException{
        // how to check destination's accessibility?
        // label_in_board=destination;
        System.out.println("WARN! This method should not be called!");
    }
}
