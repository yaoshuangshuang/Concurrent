package at.guigu;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource{
    private int flag = 1 ;  // 1：A，2：B ,3：C
     private Lock lock = new ReentrantLock();
//     一把锁三个钥匙，一对多
//    使用await singal 对应 wait notify, 注意修改暗号 ，标志位

    //三个方法合为一个方法？
       private Condition c1 = lock.newCondition();
       private Condition c2 = lock.newCondition();
       private Condition c3 = lock.newCondition();

        public void print4(){  //A
            lock.lock();
            //1 判断
            try {
                while (flag != 1)
                {
                    //A线程Parse
                    c1.await();
                }
                //2 干活
                for (int i = 0; i < 4; i++) {
                    System.out.println(Thread.currentThread().getName() + "\t" + i);
                }
                //修改标志位
                flag = 2 ;
                //3 通知 ,如何通知B
                c2.signal();
            }
            catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            }
        public void print5(){  //B
        lock.lock();
        //1 判断
        try {
            while (flag != 2)
            {
                //A线程Parse
                c2.await();
            }
            //2 干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //修改标志位
            flag = 3 ;
            //3 通知 ,如何通知B
            c3.signal();
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
        public void print6(){  //C
        lock.lock();
        //1 判断
        try {
            while (flag != 3)
            {
                //C线程Parse
                c3.await();
            }
            //2 干活
            for (int i = 0; i < 6; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //修改标志位
            flag = 1 ;
            //3 通知 ,如何通知A
            c1.signal();
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
public class ThreadOrderAccess {
    
    /*
        精确打击，通知
        A- > B > C
        A打印 4，B打印5，C打印6次，6轮
        变量加方法构成类
     */
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for (int i = 1; i <4 ; i++) {
                shareResource.print4();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i <5 ; i++) {
                shareResource.print5();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i <6 ; i++) {
                shareResource.print6();
            }
        }, "C").start();
    }

}
