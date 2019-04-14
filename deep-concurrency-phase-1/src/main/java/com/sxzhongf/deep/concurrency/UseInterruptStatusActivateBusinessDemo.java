package com.sxzhongf.deep.concurrency;

/**
 * 如果业务在等待时间内获得了结果，通过强制 {@link InterruptedException} 退出等待状态
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @since
 */
public class UseInterruptStatusActivateBusinessDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.printf("thread1 %s sleep 1 hours \n",Thread.currentThread().getName());
                try {
                    Thread.sleep(360000);
                    System.out.printf("thread1 %s awaked. \n",Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    System.out.printf("thread1 %s is interrupted while sleeping. \n",Thread.currentThread().getName());
                    return;
                }
                System.out.printf("thread1 %s leaving normally. \n",Thread.currentThread().getName());
            }
        });

        //启动子线程
        thread1.start();

        //确保子线程进入休眠状态
        Thread.sleep(1000);

        //强制激活
        thread1.interrupt();

        //等待子线程执行完毕
        thread1.join();

        System.out.println("main thread is over.");


    }
}
