public class Main{
    static{
        System.loadLibrary("nameless_chess_cpp");
    }
    
    private native void cpp_main();
    
    public static void main(String[] args){
        new Main().cpp_main();
    }
}