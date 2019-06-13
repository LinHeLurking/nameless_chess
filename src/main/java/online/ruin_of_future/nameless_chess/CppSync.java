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
    *   return value:
    *       +1 -> valid move
    *       -1 -> invalid move
    * */
    public native int sync_move(int from_x, int from_y, int to_x, int to_y);

    public native int sync_gamemode(int mode);

    public native int sync_drawboard_in_commandline();

    /*
     *   this is used for testing library loading
     *   return value:
     *       1 -> work well
     * */
    public native int sync_nothing();
}
