package thread.sync;

public class VolatileFlagExample {

    private static volatile boolean  running = true;

    public static void main(String[] args) throws InterruptedException {
        Thread worker = new Thread(() -> {
            while(running){
                Thread.onSpinWait();
            }
            System.out.println("종료");
        });

        worker.start();
        Thread.sleep(100);

        running = false;
        worker.join();
    }
}
