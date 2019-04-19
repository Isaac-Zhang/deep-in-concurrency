package com.sxzhongf.deep.concurrency;

/**
 * 用户线程 & 守护线程
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @since
 */
public class DaemonThreadDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (;;){}
            }
        });
        //设置为守护进程
        //thread.setDaemon(true);
        //启动子线程
        thread.start();
        System.out.println("main thread is over...");
    }
}
