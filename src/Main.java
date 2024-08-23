import java.util.concurrent.ExecutionException;

public class Main {
    /**
     * Uncomment object of every problem statement (one at a time) to see the task in action.
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        PS1 ps1 = new PS1();
//        ps1.execute();

//        PS2 ps2 = new PS2();
//        ps2.execute();

        PS3 ps3 = new PS3();
        ps3.execute();

//        PS9 ps9 = new PS9();
//        ps9.execute();


        // Some classes are using thread pools as static member. So, to exit the program gracefully this is required.
        System.exit(0);
    }
}