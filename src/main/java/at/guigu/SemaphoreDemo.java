package at.guigu;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {

    /*
        多对多 ，并且有加有减
        Semaphore(int permits) 传入参数，公平锁
        “信号”
        例子： 上厕所
        多对一，使用synchronized
    */
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);//3个厕所

        for (int i = 1; i <= 6; i++) { //6个人
            new Thread(() -> {
                boolean flag = false ;
                try {
                    semaphore.acquire();
                    //获取钥匙
                    flag = true;//表示进入
                    System.out.println(Thread.currentThread().getName() + "\t 抢到厕所");
                    try {
                        //等待几秒钟
                        TimeUnit.SECONDS.sleep(new Random().nextInt(4));
                        //人已经离开厕所
                        System.out.println(Thread.currentThread().getName() + "\t 离开厕所");
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();

                    } finally {
                        if(flag){
                            //释放
                            semaphore.release();
                        }
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
