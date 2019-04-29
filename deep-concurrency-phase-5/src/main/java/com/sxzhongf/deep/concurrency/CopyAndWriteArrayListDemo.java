package com.sxzhongf.deep.concurrency;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 验证{@link CopyOnWriteArrayList}
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @since
 */
public class CopyAndWriteArrayListDemo {
    private static volatile CopyOnWriteArrayList<String> arrayList = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        arrayList.add("hello");
        arrayList.add("Isaac");
        arrayList.add("welcome");
        arrayList.add("to");
        arrayList.add("beijing");

        Thread thread = new Thread(()->{
            //修改元素
            arrayList.set(1,"remove");
            //删除元素
            arrayList.remove(2);
            arrayList.remove(3);
        });

        //在修改线程启动之前获取迭代器内容
        Iterator<String> iterator = arrayList.iterator();
        thread.start();

        //等待修改线程结束
        thread.join();
        //迭代输出元素
        while (iterator.hasNext()){
            System.out.println(iterator.next());
            /**
             * hello
             * Isaac
             * welcome
             * to
             * beijing
             *
             * 从结果可以看出修改线程，没有影响到迭代器的引用数组
             */
        }
    }
}
