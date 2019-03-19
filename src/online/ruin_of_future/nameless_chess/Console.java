package online.ruin_of_future.nameless_chess;

public class Console {
    public static void launch() {
        Battle battle = new Battle();
        int status_code = battle.fight();
        switch (status_code) {
            case 0:
                System.out.println("Stops normally");
            case -1:
                System.out.println("Interrupt");
            default:
                System.out.println(String.format("Exit with code %d", status_code));
        }
    }

    public static void main(String[] arg) {
        launch();
    }
}
