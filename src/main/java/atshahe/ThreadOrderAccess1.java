package atshahe;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


public class ThreadOrderAccess1 {
    /*
          需求:精确打击，通知
            A- > B > C
            A打印 4，B打印5，C打印6次，6轮

         */
    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(4 ,() -> {
            // 不用lambda该怎么写？

            for (int i = 1; i <= 5; i++) {
                new Thread(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t B打印 ");

                }),String.valueOf(i)).start();
// cyclicBarrier: why not new Thread and  invoke start method??
            }
        });

        for (int i = 1; i <= 4; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t A打印 " );
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

    private static void start() {
    }


}
