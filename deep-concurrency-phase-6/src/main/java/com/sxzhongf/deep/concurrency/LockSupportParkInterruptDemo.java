package com.sxzhongf.deep.concurrency;

import java.util.concurrent.locks.LockSupport;

/**
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @link LockSupport} interrupt状态
 * @since
 */
public class LockSupportParkInterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread childThread = new Thread(() -> {
            System.out.println("child thread begin park");
            //调用park方法，挂起自己,只有被中断才会退出循环
            while (!Thread.currentThread().isInterrupted()) {
                //调用park方法，挂起自己
                System.out.println("wait : "+System.currentTimeMillis());
                LockSupport.park();
            }
            System.out.println("child thread unpark.");
        });

        //启动子线程
        childThread.start();

        //主线程停顿1s使子线程先执行
        Thread.sleep(1000);

        System.out.println("main thread begin unpark.");

        //中断子线程
        childThread.interrupt();
    }
}
