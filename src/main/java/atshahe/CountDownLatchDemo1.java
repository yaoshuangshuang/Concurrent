package atshahe;

import at.guigu.CountDownLatchDemo;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo1 {

    public static void main(String[] args) throws InterruptedException {
//先new出资源类
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 准备发射，倒计时：" + (10 - finalI));
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t 火箭发射成功 ！！");

    }
}