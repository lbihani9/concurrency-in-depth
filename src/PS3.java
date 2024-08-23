import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PS3 {
    private static Random rand = new Random();
    private static final int TOTAL_INTERACTIONS = 500;
    private static final int TOTAL_PRODUCERS = rand.nextInt(TOTAL_INTERACTIONS);
    private static final int TOTAL_CONSUMERS = TOTAL_INTERACTIONS - TOTAL_PRODUCERS;
    private static final int BUFFER_SIZE = 99;
    private static final int MAX_BUFFER_ENTRY = 3000;
    private SynchronizedBuffer buffer;

    /**
     * Using synchronized methods/statements with notify/notifyAll can start up any thread (producer / consumer). However, if we observe carefully,
     * we don't want a consumer to notify another consumer, similarly we don't want a producer to wake up another producer. Since, default synchronization
     * technique doesn't allow waking up a specific thread or threads from specific wait group, we need our own custom lock and condition variables to
     * allow producers and consumers to wait in different wait groups and wake threads from specific wait groups.
     */
    private static class SynchronizedBuffer {
        private final Queue<Integer> buffer = new LinkedList<>();
        private final ReentrantLock lock = new ReentrantLock();
        private final Condition bufferEmpty = lock.newCondition(); // consumers will wait on it whenever they can't consume.
        private final Condition bufferFull = lock.newCondition(); // producers will wait on it whenever they can't produce.

        // These atomic counts have been added to ensure that when there are very high number of producers or consumers, they don't
        // keep waiting in on their condition variables.
        private final AtomicInteger waitingProducersCount = new AtomicInteger(0);
        private final AtomicInteger waitingConsumersCount = new AtomicInteger(0);

        void push(int x) throws InterruptedException {
            lock.lock();
            try {
                waitingProducersCount.incrementAndGet();
                while (isFull()) {
                    try {
                        if (waitingConsumersCount.get() == 0) {
                            System.out.println(Thread.currentThread().getName() + ": Buffer is full. No consumer available. Dropping element.");
                            return;
                        }
                        System.out.println(Thread.currentThread().getName() + ": Buffer is full. Waiting for some space.... Current buffer size: " + buffer.size());
                        bufferFull.await();
                    } catch (InterruptedException _) {
                        System.out.println(Thread.currentThread().getName() + ": Producer interrupted. Verifying buffer capacity before pushing....");
                    }
                }
                buffer.add(x);
                // This will randomly wake up 1 waiting consumer.
                bufferEmpty.signal();
                System.out.println(Thread.currentThread().getName() + ": Pushed " + x + " to buffer");
            } finally {
                waitingProducersCount.decrementAndGet();
                lock.unlock();
            }
        }

        int pop() throws InterruptedException {
            lock.lock();
            try {
                waitingConsumersCount.incrementAndGet();
                while (isEmpty()) {
                    try {
                        if (waitingProducersCount.get() == 0) {
                           System.out.println(Thread.currentThread().getName() + ": Buffer is empty. No producer available. Exiting...");
                           return -1;
                        }
                        System.out.println(Thread.currentThread().getName() + ": Buffer is empty. Waiting for some elements to be pushed in the buffer.... Current buffer size: " + buffer.size());
                        bufferEmpty.await();
                    } catch (InterruptedException _) {
                        System.out.println(Thread.currentThread().getName() + ": Buffer is interrupted. Verifying buffer size before popping....");
                    }
                }
                int val = buffer.remove();

                // This will randomly wake up 1 waiting producer.
                bufferFull.signal();
                System.out.println(Thread.currentThread().getName() + ": Popped " + val + " from buffer");
                return val;
            } finally {
                waitingConsumersCount.decrementAndGet();
                lock.unlock();
            }
        }

        private boolean isFull() {
            lock.lock();
            try {
                return buffer.size() == BUFFER_SIZE;
            } finally {
                lock.unlock();
            }
        }

        private synchronized boolean isEmpty() {
            lock.lock();
            try {
                return buffer.isEmpty();
            } finally {
                lock.unlock();
            }
        }
    }

    private class Producer implements Runnable {
        @Override
        public void run() {
            int randomElement = rand.nextInt(MAX_BUFFER_ENTRY);
            System.out.println(Thread.currentThread().getName() + ": Trying to Produce " + randomElement);
            try {
                buffer.push(randomElement);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(Thread.currentThread().getName() + ": An error occurred while Producing " + randomElement);
                throw new RuntimeException(e);
            }
        }
    }

    private class Consumer implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": Trying to Consume....");
            try {
                buffer.pop();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(Thread.currentThread().getName() + ": An error occurred while Consuming.");
                throw new RuntimeException(e);
            }
        }
    }

    PS3() {
        System.out.println(Thread.currentThread().getName() + ": Initializing Buffer....");
        buffer = new SynchronizedBuffer();
        System.out.println(Thread.currentThread().getName() + ": Initialized Buffer. Total producers: " + TOTAL_PRODUCERS + " Total consumers: " + TOTAL_CONSUMERS);
    }

    void execute() throws InterruptedException {
        ArrayList<Thread> threads = new ArrayList<Thread>();
        for (int i = 0; i < TOTAL_PRODUCERS; i++) {
            threads.add(new Thread(new Producer()));
        }

        for (int i=0; i<TOTAL_CONSUMERS; i++) {
            threads.add(new Thread(new Consumer()));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }
}
