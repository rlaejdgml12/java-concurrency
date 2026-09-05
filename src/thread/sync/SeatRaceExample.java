package thread.sync;

import java.util.TreeMap;

public class SeatRaceExample {

    static class Seat{
        private String reservedBy = null;
        private int successCount = 0;

        boolean reserve(String name) throws InterruptedException{
            if(reservedBy == null){
                Thread.sleep(1);
                reservedBy = name;
                successCount++;
                return true;
            }
            return false;
        }

        String getReservedBy(){return reservedBy;}
        int getSuccessCount() {return successCount;}
    }

    public static void main(String[] args) throws InterruptedException {
        Seat seat = new Seat();

        Runnable userA = () -> {
            try {
                seat.reserve("A");
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        };

        Runnable userB = () -> {
            try {
                seat.reserve("B");
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        };

        Thread t1 = new Thread(userA);
        Thread t2 = new Thread(userB);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("예약자 : " + seat.getReservedBy());
        System.out.printf("성공 처리된 횟수 : " + seat.getSuccessCount());
    }

    private static Runnable getRunnable() {
        return () -> {
        };
    }
}
