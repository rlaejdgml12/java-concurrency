package practice._2026_09_09;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PoolReuseDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2); // 쓰레드풀 2개짜리 생성
        try {
            for(int i = 1; i <= 6; i++){
                int taskNumber = i; //작업 번호
                executor.execute(() -> { //스레드풀에 작업넘기기 내용은 뒤에
                    System.out.println("작업 : " + taskNumber + " 시작 / " + Thread.currentThread().getName()); //넘긴 작업 첫줄, 작업 싲가한다는 출력
                    try {
                        Thread.sleep(500); // 쓰레드 재우기
                    } catch (InterruptedException e){ //만약에 인터럽트 되면
                        Thread.currentThread().interrupt(); //그 예외를 이렇게 처리하라
                    }
                    System.out.println("작업 " + taskNumber + " 끝"); // 작업 끝
                });
            }
        } finally {
            executor.shutdown(); // 이건 뭐지
        }
    }
}
