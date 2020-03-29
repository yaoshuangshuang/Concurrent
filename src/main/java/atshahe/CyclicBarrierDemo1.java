package atshahe;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo1 {
    /*
    五个人到场才可以开会
    与CountDownLatch正好相反
     */
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5 ,() -> {
        // 不用lambda该怎么写？
            System.out.println(Thread.currentThread().getName() + "\t 抱歉，我现在宣布会议延迟到明天举行！！");
        });

        for (int i = 1; i <= 5; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 到场人数 ： " + finalI);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
