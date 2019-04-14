package com.sxzhongf.deep.concurrency;

/**
 * notify 和notifyAll方法的区别
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @since
 */
public class NotifyAndNotifyAllDiffDemo {
    //创建资源
    private static volatile Object resourceA = new Object();
    public static void main(String[] args) throws InterruptedException {

        //创建线程A
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                //获取resourceA共享资源的监视器锁
                synchronized(resourceA){
                    System.out.println("threadA got the resourceA lock...");

                    try {
                        System.out.println("threadA begin wait()...");
                        resourceA.wait();
                        System.out.println("threadA end wait()...");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //创建线程B
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                //获取resourceA共享资源的监视器锁
                synchronized(resourceA){
                    System.out.println("threadB got the resourceA lock...");

                    try {
                        System.out.println("threadB begin wait()...");
                        resourceA.wait();
                        System.out.println("threadB end wait()...");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //创建线程C
        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(resourceA){
                    System.out.println("threadC begin wait()...");

                    /**
                     * 唤醒全部notifyAll()
                     * {@code threadA got the resourceA lock...
                     * threadA begin wait()...
                     * threadB got the resourceA lock...
                     * threadB begin wait()...
                     * threadC begin wait()...
                     * threadA end wait()...
                     * threadB end wait()...
                     * main thread end. }
                     */
//                    resourceA.notifyAll();

                    /**
                     * 唤醒单个线程notify()
                     *{@code threadA got the resourceA lock...
                     * threadA begin wait()...
                     * threadB got the resourceA lock...
                     * threadB begin wait()...
                     * threadC begin wait()...
                     * threadA end wait()...}
                     */
                    resourceA.notify();
                }
            }
        });

        //启动线程
        threadA.start();
        threadB.start();
        //等待threadA & threadB都处于wait
        Thread.sleep(1000);
        threadC.start();

        //等待线程结束
        threadA.join();
        threadB.join();
        threadC.join();

        System.out.println("main thread end.");
    }
}
