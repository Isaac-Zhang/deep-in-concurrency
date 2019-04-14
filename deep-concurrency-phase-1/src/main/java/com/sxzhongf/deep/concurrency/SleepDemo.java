package com.sxzhongf.deep.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * sleep demo
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @since
 */
public class SleepDemo {
    //创建一个独占锁
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        sleepDemo1();
    }

    /**
     * 线程在sleep时拥有的监视器资源不会释放
     */
    private static void sleepDemo1() {
        //创建线程1
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                //获取独占锁
                lock.lock();
                System.out.println("thread1 is in sleep...");
                try {
                    Thread.sleep(1000);
                    System.out.println("thread1 is in awaked!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //释放锁
                    lock.unlock();
                }
            }
        });

        //创建线程2
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                //获取独占锁
                lock.lock();
                try {
                    System.out.println("thread2 is in sleep...");
                    Thread.sleep(10000);
                    System.out.println("thread2 is in awaked!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        //启动线程
        thread1.start();
        thread2.start();
    }
}
