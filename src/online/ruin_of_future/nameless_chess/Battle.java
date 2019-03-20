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
    /*
     *  notes:
     *      board stores the battle status.
     *
     *      You can check what is in some label_in_board by what_in_pos.
     *      A label_in_board is a label that represents the position in the Board graph, you can change the coordinate (x,y) \
     *          by board.get_label(x,y)
     *      If some position is not occupied(which means there is no piece on it), you'll get null from what_in_pos.
     *
     *      For the 4 players, you can access their information via players.
     *      An element in players contains a HashMap whose key is the index of some piece \
     *          and value is corresponding piece. You can get the information about a piece's label in board, owner,
     *          and its own piece index.
     * */
    public StatusBoard board = new StatusBoard();
    public ArrayList<Player> players = new ArrayList<>();
    private int player_cnt = 4;

    Battle() {
        for (int i = 0; i < player_cnt; ++i) {
            players.add(new Player(i));
        }
    }

    class StatusBoard extends Board {
        public Map<Integer, Piece> what_in_pos = new HashMap<>();

        StatusBoard() {
            super();
        }
    }

    class Piece extends __Piece {
        Piece(int owner, int piece_index, int label_in_board) {
            super(owner, piece_index, label_in_board);
        }

        @Override
        public void moveto(int destination) throws MoveException {
            if (board.is_adjacent(this.label_in_board, destination)) {
                if (board.what_in_pos.get(destination) != null) {
                    if (board.what_in_pos.get(destination).owner != this.owner) {
                        Piece in_dest = board.what_in_pos.get(destination);
                        this.label_in_board = destination;
                        players.get(in_dest.owner).pieces.remove(in_dest.index);
                        players.get(in_dest.owner).PIECE_CNT--;
                        board.what_in_pos.remove(destination);
                        board.what_in_pos.put(destination, this);
                    } else {
                        throw new MoveException();
                    }
                } else {
                    this.label_in_board = destination;
                    board.what_in_pos.put(destination, this);
                }
            } else {
                throw new MoveException();
            }

        }
    }

    class Player extends __Player {
        // the Integer of a piece is the index
        public HashMap<Integer, Piece> pieces = new HashMap<>();

        /*
         *  position of players:
         *      0
         *  1       2
         *      3
         * */

        private int[][][] init_pos = {
                {{0, 2}, {0, 4}, {1, 4}, {1, 5}, {1, 6}, {0, 6}, {0, 8}},
                {{2, 10}, {4, 10}, {4, 9}, {5, 9}, {6, 9}, {6, 10}, {8, 10}}    //,
                //{{10, 2}, {10, 3}, {9, 4}, {9, 5}, {9, 6}, {10, 6}, {10, 8}} ,
                //{{2, 0}, {4, 0}, {4, 1}, {5, 1}, {6, 1}, {6, 0}, {8, 0}}
                // coordinates of the other two are symmetric with the former ones
        };

        Player(int player_index) {
            super(player_index);
            int a, b, pos_i;
            switch (player_index) {
                default:
                case 0:
                    a = 0;
                    b = 1;
                    pos_i = 0;
                    break;
                case 1:
                    a = 0;
                    b = 1;
                    pos_i = 1;
                    break;
                case 2:
                    a = 1;
                    b = 0;
                    pos_i = 1;
                    break;
                case 3:
                    a = 1;
                    b = 0;
                    pos_i = 0;
            }
            for (int i = 0; i < Player.MAX_PIECE; ++i) {
                pieces.put(i, new Piece(player_index, i, init_pos[pos_i][a][b]));
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
