package atshahe;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo1 {
    /*
    多对多情况下
    抢占停车厂
     */
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        //3个停车场,也就是资源类与使用的新技术有关
        for (int i = 1; i <=5 ; i++) {
            //5个人，循环5次
            new Thread(() -> {
                boolean flag = false;
                //注意：flag要设置为false，默认为true,否则出现线程不安全的问题
                //条件判断时，一般为false
                  flag = true ;

                  //如果为真
                try {
                    semaphore.acquire();
                    //获得许可
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //判断
                System.out.println(Thread.currentThread().getName() + "\t 抢到车位");
                try {
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + "\t 离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    if(flag){

                        //释放
                        semaphore.release();
                }

                }
            },String.valueOf(i)).start();
        }


    }
}
