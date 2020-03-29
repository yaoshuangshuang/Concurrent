package at.guigu;

import java.util.Collection;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        /*
        测试辅助类，有点难度了
         例子：火箭发射倒计时，书写程序
          抢车位：   可以对资源限流和扩容
      多对一时 ，使用lock锁

         */
        CountDownLatch countDownLatch = new CountDownLatch(6);
        // 计数， 下降 ， 抓住
        for (int i = 0; i <6 ; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 火箭发射倒计时 ：" + (7- finalI));
                countDownLatch.countDown();
                //锁存数计数器每次减一，直到0 ，相当于 闹钟
            },String.valueOf(i)).start();
//            多线程的启动不会区分谁前谁后

        }
        countDownLatch.await();
//        导致当前线程等待，直到锁存器计数到零，除非线程被中断。
        //起床了
        System.out.println(Thread.currentThread().getName() + "\t 火箭发射");

    }
}
