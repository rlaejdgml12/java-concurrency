package practice._2026_09_07;

public class VolatileCounterDemo {
    static volatile int count = 0;

    static void increment(){
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for(int i = 0; i < 100_000; i++){
                increment();
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(count);
    }
}
