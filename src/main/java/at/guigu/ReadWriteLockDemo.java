package at.guigu;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
    要求：同一资源10个线程读，10个线程写入
    演示：1，不加锁
    2，lock锁
    3，加读写锁
    使用同步块，在任何给定时间点仅允许一个线程访问一种方法。这是非常昂贵的操作。
    lock锁通过允许出于不同目的配置各种锁来避免这种情况。一个锁下可以同步几个方法，
    而另一个锁下可以同步其他方法。这允许更多的并发性，也可以提高整体性能
 */
class Person {
        private volatile  Map<String,String> map = new HashMap<>();

       ReadWriteLock readWriteLock  = new ReentrantReadWriteLock();


    public void write(String key , String value){
//            lock.lock();//获取锁
        readWriteLock.writeLock().lock();

            try {
                System.out.println(Thread.currentThread().getName() + " \t 正在输入");

                map.put(key, value);
                System.out.println(Thread.currentThread().getName() + " \t 结束写入" + key);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                readWriteLock.writeLock().unlock();
            }

        }
            public void read(String key){
//                readWriteLock.readLock().lock();
                //读可以不加锁

                try {
                    System.out.println(Thread.currentThread().getName() + " \t 正在读取");

                    String result = map.get(key);

                    System.out.println(Thread.currentThread().getName() + " \t 结束读取 " + result);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
//                    readWriteLock.readLock().unlock();
                }

            }

        }


public class ReadWriteLockDemo {
     public static void main(String[] args) {

         Person person = new Person();

         for (int i = 0; i <10 ; i++) {
            final int tmp = i;
             new Thread(() -> {

                    person.write(tmp+"", tmp+"");
//                 获取当前线程的名称，传入参数字符串和对象？

             },String.valueOf(i)).start();
         }

         for (int i = 0; i <10 ; i++) {
             final int tmp = i;
             new Thread(() -> {

                 person.read(tmp+"");

             },String.valueOf(i)).start();
         }
     }
}
