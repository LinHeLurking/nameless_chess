package online.ruin_of_future.nameless_chess;

public class __Piece {
    int owner;
    int index;
    int pos;

    __Piece(int owner, int index, int pos) {
        this.owner = owner;
        this.index = index;
        this.pos = pos;
    }

    void moveto(int destination) throws MoveException{
        // how to check destination's accessibility?
        // pos=destination;
        System.out.println("WARN! This method should not be called!");
    }
}
