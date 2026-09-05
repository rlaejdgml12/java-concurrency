package thread.sync;

public class SafeBankAccount {
    static class Account{

        private int balance;
        private int successCount;
        private final Object lock = new Object();

        public Account(int balance){
            this.balance = balance;
        }

        public int getBalance() {
            synchronized (lock) {
                return this.balance;
            }
        }
        public int getSuccessCount(){
            synchronized (lock) {
                return this.successCount;
            }
        }

        public boolean withdraw(int amount) throws InterruptedException {
            synchronized (lock) {
                if (this.balance >= amount) {
                    Thread.sleep(1);
                    this.balance -= amount;
                    successCount++;
                    return true;
                }
                return false;
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Account account = new Account(1000);


        Runnable task1 = () -> {
            try {
                account.withdraw(600);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        };

        Runnable task2 = () -> {
            try {
                account.withdraw(600);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        };

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("최종 잔액 : " + account.getBalance());
        System.out.println("성공 처리된 횟수 : " + account.getSuccessCount());

    }
}
