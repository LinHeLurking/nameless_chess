package online.ruin_of_future.nameless_chess;

public class CppSync {

    private static String path_escape(String str) {
        char[] str_char = str.toCharArray();
        for (int i = 0; i < str_char.length; ++i) {
            if (str_char[i] == '\\') {
                str_char[i] = '/';
            }
        }
        return String.valueOf(str_char);
    }

    static {
        String currentDirectory = System.getProperty("user.dir");
        // System.out.println("current directory is " + currentDirectory);
        try {
            String ldp = currentDirectory + "/lib/" + "nameless_chess_cpp.dll";
            ldp = path_escape(ldp);
            System.out.println("trying to loading lib in " + ldp);
            System.load(ldp);
        } catch (Exception e) {
            String ldp = currentDirectory + "/" + "nameless_chess_cpp";
            ldp = path_escape(ldp);
            System.out.println("trying to loading lib in " + ldp);
            System.load(ldp);
        }
    }


    /*
     *   pass move events retrieved from button click to cpp lib
     *   return value:
     *       2 -> invalid move
     *       1 -> valid move -> plain move without eating
     *       0 -> valid move -> eat a piece
     * */
    public native int sync_java_manual_move(int from_x, int from_y, int to_x, int to_y);

    /*
     *   get cpp lib automatic piece moving
     *
     *   you shall use sync_cpp_auto_move() instead of this raw function,
     *   since this integer return value could not be understand easily.
     *
     *   return value == (fromx_x*11+from_y)*1000 + (to_x*11+to_y)
     * */
    private native int sync_cpp_auto_move__raw();

    /*
     *  pass mode to cpp
     *  return value:
     *      +1 -> success
     *      -1 -> failure
     * */
    public native int sync_gamemode(int mode);



    /*
     *   this is used for testing library loading
     *   return value:
     *       1 -> work well
     * */
    public native int sync_nothing();

    public native int sync_drawboard_in_commandline();



    // 

    // not finished
    public int sync_cpp_auto_move() {
        // something
        int move = sync_cpp_auto_move__raw();
        int to = move % 1000;
        int from = move / 1000;

        int from_x = from / 11;
        int from_y = from % 11;
        int to_x = to / 11;
        int to_y = to % 11;
        return 0;
    }
}
