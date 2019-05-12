package com.sxzhongf.deep.Customizer;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;

/**
 * TODO
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @since
 */
public class DemoFromJDMain {
    final static DemoFromJD lock = new DemoFromJD();
    final static Condition notFull = lock.newCondition();
    final static Condition notEmpty = lock.newCondition();
    final static Queue<String> queue = new LinkedBlockingQueue<String>();
    final static int queueSize = 10;

    public static void main(String[] args) throws InterruptedException {
        Thread producer = new Thread(new Runnable() {
            public void run() {
                // 获取独占锁
                lock.lock();
                try {
                    // (1)如果队列满了，则等待
                    while (queue.size() == queueSize) {
                        notEmpty.await();
                    }
                    // (2)添加元素到队列
                    System.out.println(queue.add("ele"));
                    // (3)唤醒消费线程
                    notFull.signalAll();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally { // 释放锁
                    lock.unlock();
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            public void run() {
                // 获取独占锁
                lock.lock();
                try {// 队列空，则等待
                    while (0 == queue.size()) {
                        notFull.await();
                    }
                    // 消费一个元素
                    String ele = queue.poll(); // 唤醒生产线程
                    System.out.println(ele);
                    notEmpty.signalAll();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally { // 释放锁
                    lock.unlock();
                }
            }
        });
        // 启动线程
        producer.start();
        consumer.start();

        Thread.currentThread().join();
    }

}