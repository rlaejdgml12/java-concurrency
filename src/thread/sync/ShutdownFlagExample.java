package thread.sync;

public class ShutdownFlagExample {

    static class Worker{
        volatile boolean flag = true;

        public void run() throws InterruptedException{
            while(flag){
                Thread.sleep(1000);
                System.out.println("작업 중...");
            }
        }

        public void shutdown(){
            flag = false;
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Worker worker = new Worker();
        Runnable task = () -> {
            try {
                worker.run();
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        };

        Thread thread = new Thread(task);

        thread.start();
        Thread.sleep(3000);
        worker.shutdown();
        thread.join();
        System.out.println("종료 완료");

    }

}
