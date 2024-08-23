import java.util.ArrayList;
import java.util.Random;

public class PS2 {
    private static final Random rand = new Random();
    private static final int MAX_BALANCE = 10000;
    private static final int TOTAL_TRANSACTIONS = 20;
    private static final int TOTAL_DEPOSITORS = rand.nextInt(TOTAL_TRANSACTIONS);
    private static final int TOTAL_WITHDRAWERS = TOTAL_TRANSACTIONS - TOTAL_DEPOSITORS;
    private Account account;

    private class Account {
        private int balance;

        Account() {
            balance = rand.nextInt(MAX_BALANCE);
            System.out.println("New account successfully created. Current balance: $" + balance);
        }

        synchronized void deposit(int amount) {
            balance += amount;
            System.out.println(Thread.currentThread().getName() + ": $" + amount + " successfully deposited. Current balance: $" + balance);
        }

        synchronized void withdraw(int amount) {
            if (balance < amount) {
                System.out.println(Thread.currentThread().getName() + ": Insufficient balance. Cannot withdraw $" + amount + " from the account. Current balance: $" + balance);
                return;
            }
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + ": $" + amount + " successfully withdrawn. Current balance: $" + balance);
        }
    }

    private class Withdrawer implements Runnable {
        @Override
        public void run() {
            int amount = rand.nextInt(MAX_BALANCE);
            System.out.println(Thread.currentThread().getName() + ": Trying to withdraw $" + amount + " from the account");
            account.withdraw(amount);
        }
    }

    private class Depositor implements Runnable {
        @Override
        public void run() {
            int amount = rand.nextInt(MAX_BALANCE);
            System.out.println(Thread.currentThread().getName() + ": Trying to deposit $" + amount + " to the account");
            account.deposit(amount);
        }
    }

    PS2() {
        account = new Account();
    }

    void execute() throws InterruptedException {
        ArrayList<Thread> threads = new ArrayList<Thread>();
        for (int i = 0; i < TOTAL_DEPOSITORS; i++) {
            threads.add(new Thread(new Depositor()));
        }

        for (int i=0; i<TOTAL_WITHDRAWERS; i++) {
            threads.add(new Thread(new Withdrawer()));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }
}
