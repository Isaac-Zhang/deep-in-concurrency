package com.sxzhongf.deep.concurrency;

import java.util.ArrayList;
import java.util.List;

/**
 * 对象等待和唤醒,死锁DEMO
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @since
 */
public class WaitAndNotifyDeathLockDemo {

    //创建资源
    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) throws InterruptedException {

        //创建线程
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //获取resourceA共享资源的监视器锁
                    synchronized (resourceA) {
                        System.out.printf("threadA got resourceA lock, name's [%s] \n", Thread.currentThread().getName());
                        //获取resourceB共享资源的监视器锁
                        synchronized (resourceB) {
                            System.out.printf("threadA got resourceB lock, name's [%s] \n", Thread.currentThread().getName());
                            //线程A阻塞，并释放获取到的resourceA的锁
                            System.out.println("threadA release resourceA lock...");
                            resourceA.wait();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //创建线程
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                //线程休眠1s，防止同时获取
                try {
                    Thread.sleep(1000);
                    //获取resourceA共享资源的监视器锁
                    synchronized (resourceA) {
                        System.out.printf("threadB got resourceA lock, name's [%s] \n", Thread.currentThread().getName());
                        //获取resourceB共享资源的监视器锁
                        synchronized (resourceB) {
                            System.out.printf("threadB got resourceB lock, name's [%s] \n", Thread.currentThread().getName());
                            //线程B阻塞，并释放获取到的resourceA的锁
                            System.out.println("threadB release resourceA lock...");
                            resourceA.wait();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //启动线程
        threadA.start();
        threadB.start();

        //等待两个线程结束
        threadA.join();
        threadB.join();
    }
}
