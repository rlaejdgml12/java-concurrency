package thread.create;

public class StartVsRun {
    public static void main(String[] args) {
        Runnable task = () -> System.out.println(
                Thread.currentThread().getName() + " 실행"
        );

        Thread worker1 = new Thread(task, "worker-1");
        worker1.start();

        Thread worker2 = new Thread(task, "worker-2");
        worker2.start();
    }
}
