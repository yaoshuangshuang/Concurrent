package at.guigu;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    /*
     runable接口可以使用lambda表达式
       想法：循环开始,集齐七颗龙珠才能召唤神龙，先到先等着
     */
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,() -> {
            //循环  障碍
            //传入龙珠的个数
            System.out.println("七颗龙珠已集齐，召唤神龙！！");
        });
        for (int i = 1; i <8 ; i++) {
            int finalI = i;
            new Thread(() -> {

                try {
                    System.out.println(Thread.currentThread().getName() + "\t 收集到 ：" + finalI + " \t龙珠");
                    cyclicBarrier.await();

                    //所有执行完以后，await唤醒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } finally {
                }
            },String.valueOf(i)).start();
        }
    }


}
