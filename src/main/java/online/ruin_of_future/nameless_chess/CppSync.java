package online.ruin_of_future.nameless_chess;

public class CppSync {

    static{
        System.loadLibrary("nameless_chess_cpp");
    }

    private native int sync_move(int from_x, int from_y, int to_x, int to_y);
    private native int sycn_gamemode(int mode);
}
