package practice._2026_09_07;

import java.util.TreeMap;

public class DeadlockDemo {

    private static final Object LOCK_A = new Object();
    private static final Object LOCK_B = new Object();

    public static void main(String[] args) throws InterruptedException {
        Runnable task1 = () ->{
            synchronized (LOCK_A){
                sleep();
                synchronized (LOCK_B){
                    System.out.println("스레드 1 완료");
                }
            }
        };

        Runnable task2 = () ->{
            synchronized (LOCK_A){
                sleep();
                synchronized (LOCK_B){
                    System.out.println("스레드 2 완료");
                }
            }
        };

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();

        System.out.println("메인 종료");
    }

    public static void sleep(){
        try {
            Thread.sleep(100);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

}
