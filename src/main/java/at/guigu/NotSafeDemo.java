package at.guigu;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class NotSafeDemo {
   //3个线程，数据一致性不对，但是程序没有error
    //单独测试某个类，不用全部加载所有类
    public static void main(String[] args) {
       List<String> list =
    //           new ArrayList<String>();
       new CopyOnWriteArrayList<>();
        for (int i = 1; i <=30; i++){
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,6));

                System.out.println(list);
                //打印输出时还在添加数据，所以报并发修改异常
            },String.valueOf(i)).start();
//            Java字符串的valueOf（）方法不同类型的值转换成字符串
                    //为啥括号总在最后？
        }
    }


}
