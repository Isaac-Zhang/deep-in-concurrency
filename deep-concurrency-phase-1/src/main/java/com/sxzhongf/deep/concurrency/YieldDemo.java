package com.sxzhongf.deep.concurrency;

/**
 * How to use yield method
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @since
 */
public class YieldDemo implements Runnable {

    public YieldDemo() {
        //创建并启动线程
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            //当i=0 时让出CPU执行权，放弃时间片，进行下一轮调度
            if ((i % 5) == 0){
                System.out.println(Thread.currentThread()+" : yield cpu...");
                Thread.yield();
            }
        }

        System.out.println(Thread.currentThread()+" is over.");
    }

    public static void main(String[] args) {
        new YieldDemo();
        new YieldDemo();
        new YieldDemo();
    }
}
