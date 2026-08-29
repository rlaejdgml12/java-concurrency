package thread.create;

public class CooperativeShutdown {
    public static void main(String[] args) throws InterruptedException{
        Runnable task = () -> {
            try {
                while(!Thread.currentThread().isInterrupted()){
                    System.out.println("작업중 : " + Thread.currentThread().getName());
                    Thread.sleep(300);
                }
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                System.out.println("종료");
            }
        };

        Thread worker = new Thread(task, "worker");
        worker.start();
        worker.interrupt();
        worker.join();
        System.out.println("main 종료");
    }
}
