package thread.create;

public class InheritThread {
    static class MyThread extends Thread{
        public MyThread(String name){
            super(name);
        }

        @Override
        public void run(){
            System.out.println("[상속] " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        MyThread worker = new MyThread("inherit-1");
        worker.start();

    }
}
