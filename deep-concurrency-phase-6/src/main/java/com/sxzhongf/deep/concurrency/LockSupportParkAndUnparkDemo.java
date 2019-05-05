package com.sxzhongf.deep.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * {@link LockSupport}
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @since
 */
public class LockSupportParkAndUnparkDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread childThread = new Thread(()->{
            System.out.println("child thread begin park");

            //调用park方法，挂起自己
            LockSupport.park();

            System.out.println("child thread unpark.");
        });

        //启动子线程
        childThread.start();

        //主线程停顿1s使子线程先执行
        Thread.sleep(1000);

        System.out.println("main thread begin unpark.");

        //调用unpark方法，让childThread线程持有许可证，然后park方法返回
        LockSupport.unpark(childThread);
    }
}
