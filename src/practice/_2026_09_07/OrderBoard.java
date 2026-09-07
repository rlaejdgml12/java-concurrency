package practice._2026_09_07;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class OrderBoard {
    private final List<String> orders = new ArrayList<>();
    private final Object lock = new Object();

    public void addOrder(String raw){
        String item = raw.trim();
        if (item.isEmpty()) { return; }
        synchronized (lock) {
            orders.add(item);
        }
        System.out.println("주문 추가 : " + item);

    }

    public boolean addIfBelow(String item, int limit) throws InterruptedException {
        if(!item.isEmpty()) {
            synchronized (lock) {
                if (orders.size() < limit) {
                    Thread.sleep(1);
                    orders.add(item);
                    return true;
                }
            }
        }
        return false;
    }

    public List<String> snapshot(){
        List<String> copyList;
        synchronized (lock) {
            copyList = List.copyOf(orders);
        }
        return copyList;
    }

    public static void main(String[] args) throws InterruptedException {
        OrderBoard order = new OrderBoard();

        Runnable task = () -> {
            for(int i = 0; i < 20; i++){
                try {
                    order.addIfBelow("주문", 50);
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }

            }
        };

        Thread[] threads = new Thread[10];
        for(int i = 0; i < 10; i++){
            threads[i] = new Thread(task);
        }

        for(int i = 0; i < 10; i++){
            threads[i].start();
        }

        for(int i = 0; i < 10; i++){
            threads[i].join();
        }

        System.out.println(order.snapshot().size());

    }

}
