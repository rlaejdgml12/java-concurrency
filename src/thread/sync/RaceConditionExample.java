package thread.sync;

public class RaceConditionExample {

    static class Counter{
        private int count;

        void increment(){
            count++;
        }

        int value(){
            return count;
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Counter counter = new Counter();

        Runnable task = () -> {
            for(int i = 0; i <100_000; i++){
                counter.increment();
            }
        };

        Thread first = new Thread(task);
        Thread second = new Thread(task);

        first.start();
        second.start();

        first.join();
        second.join();

        System.out.println(counter.value());
    }
}
