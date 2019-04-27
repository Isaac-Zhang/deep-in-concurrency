package com.sxzhongf.deep.concurrency;

/**
 * TODO
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @since
 */
public class JVMReorderDemo {

    private static boolean ready = false;
    private static int num = 0;

    public static void main(String[] args) throws InterruptedException {
        //启动读取线程
        ReadThread rt = new ReadThread();
        rt.start();

        //启动写入线程
        WriteThread wt = new WriteThread();
        wt.start();

        //主线程休眠10微秒
        Thread.sleep(10);
        rt.interrupt();

        rt.join();
        wt.join();
        System.out.println("main thread exit.");

    }

    public static class ReadThread extends Thread {
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                if (ready) {
                    System.out.println("num+num = " + (num + num));
                }
                System.out.println("read thread...");
            }
        }
    }

    public static class WriteThread extends Thread {
        public void run() {
           num = 2;
           ready = true;
           System.out.println("write thread set over...");
        }
    }

}
