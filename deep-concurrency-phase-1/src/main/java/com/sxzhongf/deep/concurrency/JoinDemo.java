package com.sxzhongf.deep.concurrency;

/**
 * test {@link Thread} join method
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @since
 */
public class JoinDemo {
    public static void main(String[] args) throws InterruptedException {
//        howToUseJoin();
        joinInterrupt();
    }

    private static void joinInterrupt(){
        //线程1
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread1 begin run...");
                for (;;){}
            }
        });
        //获取主线程
        final Thread mainThread = Thread.currentThread();

        //线程2
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //中断主线程
                mainThread.interrupt();
            }
        });

        //启动线程1
        thread1.start();

        //延迟1秒启动线程2
        thread2.start();

        try {
            thread1.join();
        } catch (InterruptedException e) {
            System.err.println("main thread : "+e);
        }
    }

    /**
     * How to use join method
     * @throws InterruptedException
     */
    private static void howToUseJoin() throws InterruptedException {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread  child one over!");
            }
        });
        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("threadTwo  child over!");
            }
        });

        threadOne.start();
        threadTwo.start();

        System.out.println("Wait all child thread over!");

        //等待子线程执行完毕，返回
        threadOne.join();
        threadTwo.join();
    }
}
