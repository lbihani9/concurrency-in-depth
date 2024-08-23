import java.util.*;
import java.util.concurrent.*;

public class PS9 {
    private ArrayList<Integer> arrayList;
    private int actualSum = 0;
    static final int MIN = 0;
    static final int MAX = 5000;
    static final int ARRAY_LEN = 200;
    static final int WORKER_COUNT = 6; // Intentionally taken to ensure that WORKER_COUNT doesn't completely divide ARRAY_LEN.
    static ExecutorService threadPool = Executors.newFixedThreadPool(WORKER_COUNT);

    private class Summer implements Callable<Integer> {
        int left, right;

        Summer(int left, int right) {
            this.left = left;
            this.right = Math.min(right, ARRAY_LEN - 1);
        }

        @Override
        public Integer call() throws Exception {
            if (left > right) {
                return 0;
            }

            int sum = 0;
            for (int i = left; i <= right; i++) {
                sum += arrayList.get(i);
            }
            return sum;
        }
    }

    PS9() {
        arrayList = new ArrayList<>(Collections.nCopies(ARRAY_LEN, 0));

        Random rand = new Random();
        for (int i = 0; i < ARRAY_LEN; i++) {
            arrayList.set(i, rand.nextInt(MAX - MIN + 1) + MIN);
            actualSum += arrayList.get(i);
        }
    }

    public void execute() throws ExecutionException, InterruptedException {
        int batchSize = ARRAY_LEN / WORKER_COUNT;
        Queue<Future<Integer>> futures = new LinkedList<>();

        for (int i = 0; i < ARRAY_LEN; i += batchSize) {
            futures.offer(findSum(i, i + batchSize - 1));
        }

        int sum = 0;
        while (!futures.isEmpty()) {
            Future<Integer> future = futures.poll();
            if (!future.isDone()) {
                futures.offer(future);
                continue;
            }

            sum += future.get();
        }

        System.out.println("PS9: Actual Sum = " + actualSum + ", Sum calculated by the threads = " + sum);
    }

    private Future<Integer> findSum(int l, int r) {
        Callable<Integer> summer = new Summer(l, r);
        return threadPool.submit(summer);
    }
}
