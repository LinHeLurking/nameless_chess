package online.ruin_of_future.nameless_chess;

public class CppSync {

    static{
        try {
            System.loadLibrary("nameless_chess_cpp");
        }catch (Exception e){
            System.loadLibrary("lib/nameless_chess_cpp");
        }
    }


    private native int sync_move(int from_x, int from_y, int to_x, int to_y);
    private native int sync_gamemode(int mode);
    // this is a method used to test library loading
    private native int sync_nothing();
}
