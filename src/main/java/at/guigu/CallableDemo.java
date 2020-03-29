package at.guigu;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import static sun.rmi.transport.TransportConstants.Call;
/*
  为什么Thread中，Runable接口可以作为参数值传入？
 */
class MyThread implements Callable<String>{
    @Override
    public String call() throws Exception {
        System.out.println("此方法被调用");
        return "hello yaoshuang";
    }

}
public class CallableDemo {
    public static void main(String[] args) {
        FutureTask<String> futureTask = new FutureTask<>(new MyThread());
        Thread t = new Thread(futureTask, "A");
        t.start();
    }
}

