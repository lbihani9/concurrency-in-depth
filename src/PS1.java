import java.util.ArrayList;

public class PS1 {
    private static final int MAX = 20;
    private static final int THREAD_COUNT = 5;

    private class RunnablePrinter implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= MAX; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }

    private class ThreadPrinter extends Thread {
        @Override
        public void run() {
            for (int i = 1; i <= MAX; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }

    void execute() throws InterruptedException {
        int runnablePrinters = THREAD_COUNT / 2;
        int threadPrinters = THREAD_COUNT - runnablePrinters;

        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < runnablePrinters; i++) {
            Thread t = new Thread(new RunnablePrinter());
            threads.add(t);
            t.start();
        }

        for (int i = 0; i < threadPrinters; i++) {
            Thread t = new ThreadPrinter();
            threads.add(t);
            t.start();
        }

        // If we don't 'join' the threads, the program will stop even before they complete their execution.
        // Hence, we'd see partial or no print statements.
        for (Thread t : threads) {
            t.join();
        }
    }
}
