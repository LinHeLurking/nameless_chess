package online.ruin_of_future.nameless_chess;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


class MoveException extends Exception {
    MoveException() {
        super("Destination not accessible or already occupied");
    }
}


class Battle {
    private StatusBoard board = new StatusBoard();
    private ArrayList<Player> players = new ArrayList<>();
    private int player_cnt = 4;

    private int[][] piece_init_pos =
            {{50, 51, 52, 53, 54, 55, 56},
                    {57, 58, 59, 60, 61, 62, 63},
                    {64, 65, 66, 67, 68, 69, 70},
                    {71, 72, 73, 74, 75, 76, 77}};
    /*
     * piece_pos:
     *
     *       0
     *
     *   3       1
     *
     *       2
     * */

    Battle() {
        for (int i = 0; i < player_cnt; ++i) {
            players.add(new Player(i));
        }
    }

    class StatusBoard extends Board {
        Map<Integer, Piece> what_in_pos = new HashMap<>();

        StatusBoard() {
            super();
        }
    }

    class Piece extends __Piece {
        Piece(int owner, int index, int pos) {
            super(owner, index, pos);
        }

        @Override
        public void moveto(int destination) throws MoveException {
            if (board.is_adjacent(this.pos, destination)) {
                if (board.what_in_pos.get(destination) != null) {
                    if (board.what_in_pos.get(destination).owner != this.owner) {
                        Piece in_dest = board.what_in_pos.get(destination);
                        this.pos = destination;
                        players.get(in_dest.owner).pieces.remove(in_dest.index);
                        players.get(in_dest.owner).PIECE_CNT--;
                        board.what_in_pos.remove(destination);
                        board.what_in_pos.put(destination, this);
                    } else {
                        throw new MoveException();
                    }
                } else {
                    this.pos = destination;
                    board.what_in_pos.put(destination, this);
                }
            } else {
                throw new MoveException();
            }

        }
    }

    class Player extends __Player {
        HashMap<Integer, Piece> pieces = new HashMap<>();

        Player(int code) {
            super(code);
            for (int i = 0; i < PIECE_CNT; ++i) {
                Piece npiece = new Piece(this.code, i, piece_init_pos[this.code][i]);
                pieces.put(i, npiece);
                board.what_in_pos.put(piece_init_pos[code][i], npiece);
            }
        }
    }

    //false for no winner
    private boolean winner_check() {
        for (Player p : players) {
            if (p.PIECE_CNT == 0) {
                return true;
            }
        }
        return false;
    }

    private Pair<Integer, Integer> get_command() {
        Scanner stdin = new Scanner(System.in);
        int piece_to_move = stdin.nextInt();
        if (piece_to_move == -1) {
            return new Pair<>(-1, -1);
        } else {
            Integer destination = stdin.nextInt();
            return new Pair<>(piece_to_move, destination);
        }
    }

    private Pair<Integer, Integer> get_command(Pair<Integer, Integer> cmd) {
        return cmd;
    }

    int fight() {
        //TODO: finish fight process
        int cur_player = 0;
        do {
            System.out.println(String.format("Round for player %d", cur_player));
            //TODO: interact here
            System.out.println("Input the piece you want to move and the input the destination\n\t(-1 to exit)");

            Integer piece_to_move, destination;
            Pair<Integer, Integer> pair = get_command();
            piece_to_move = pair.getKey();
            destination = pair.getValue();
            if (piece_to_move == -1) {
                return -1;
            } else {
                try {
                    players.get(cur_player).pieces.get(piece_to_move).moveto(destination);
                    // change player
                    cur_player = (cur_player + 1) % player_cnt;
                } catch (NullPointerException | MoveException e) {
                    System.out.println(e.getMessage());
                }
            }
        } while (!winner_check());
        return 0;
    }
}
