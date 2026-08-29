package thread.create;

public class JoinPractice {
    public static void main(String[] args) throws InterruptedException{
        Runnable task = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " 작업 끝");
        };

        Thread w1 = new Thread(task, "w1");
        Thread w2 = new Thread(task, "w2");
        Thread w3 = new Thread(task, "w3");

        long time1 = System.nanoTime();

        w1.start();
        w2.start();
        w3.start();

        w1.join();
        w2.join();
        w3.join();

        long time2 = System.nanoTime();



        System.out.println("전부 완료, 걸린 시간 : " + (time2 - time1) );

    }
}
