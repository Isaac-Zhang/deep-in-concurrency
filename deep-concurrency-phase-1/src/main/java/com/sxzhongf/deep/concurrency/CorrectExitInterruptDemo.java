package com.sxzhongf.deep.concurrency;

/**
 * 线程如何使用Interrupt 优雅退出
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @since
 */
public class CorrectExitInterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()){
                    System.out.println(Thread.currentThread() + " hello.");
                }
            }
        });

        //启动子线程
        thread.start();

        //主线程休眠1s, 以便中断前 让子线程可输出内容
        Thread.sleep(1000);

        //中断子线程
        System.out.println("main thread interrupt child thread.");
        thread.interrupt();

        //等待子线程执行完毕
        thread.join();
        System.out.println("main is over.");

    }
}
