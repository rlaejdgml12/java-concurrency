package thread.create;

public class RunnableWay {
    static class MyTask implements Runnable{
        @Override
        public void run(){
            System.out.println("작업 : " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        Thread worker = new Thread(new MyTask(), "impl-1");
        worker.start();

        Runnable run = () -> System.out.println("람다 : " + Thread.currentThread().getName());
        Thread worker2 = new Thread(run, "lambda-1");
        worker2.start();
    }
}
