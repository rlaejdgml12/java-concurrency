package practice._2026_09_13;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class UnsafeCountDemo {

    static int count = 0;

    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(4);
        Set<String> names = ConcurrentHashMap.newKeySet();

        for(int i = 0; i < 1000; i++){
            pool.execute(() -> {
                count++;
                names.add(Thread.currentThread().getName());
            });
        }

        pool.shutdown();

        try {
            boolean finished = pool.awaitTermination(10, TimeUnit.SECONDS);
            if (!finished) {
                pool.shutdownNow();
            }
        } catch (InterruptedException e) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("처리된 작업 수 : " + count + ", 일한 스레드 종류 수 : " + names.size());

    }
}
