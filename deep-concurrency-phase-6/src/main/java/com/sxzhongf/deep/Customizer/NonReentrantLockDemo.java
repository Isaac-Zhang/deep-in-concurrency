package com.sxzhongf.deep.Customizer;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Condition;

/**
 * 使用自定义不可重入独享锁
 * {@link CustomizerNonReentrantLock}
 * 实现 简单的
 * 生产者<-->消费者模型
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @since
 */
public class NonReentrantLockDemo {
    final static CustomizerNonReentrantLock lock = new CustomizerNonReentrantLock();
    final static  Condition notFullCondition = lock.newCondition();
    final static  Condition notEmptyCondition = lock.newCondition();

    final static  Queue<String> queue = new LinkedBlockingQueue<>();
    final static  int queueSize = 10;

    public static void main(String[] args) throws InterruptedException {

        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                {
                    //获取独占锁
                    lock.lock();
                    try {
                        // 如果队列满了，条件等待开启
                        while (queue.size() == queueSize) {
                            notEmptyCondition.await();
                        }
                        // 添加元素入队列
                        queue.add("zhangpan");
                        // 唤醒消费队列
                        notFullCondition.signalAll();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    } finally {
                        lock.unlock();
                    }
                }
            }
        });

        Thread consumer = new Thread(() -> {
            //获取锁
            lock.lock();
            try {
                while (0 == queue.size()) {
                    notFullCondition.await();
                }
                //消费一个元素
                String zp = queue.poll();
                System.out.println("当前线程中consumer数量：" + queue.size());
                //唤醒生产线程
                notEmptyCondition.signalAll();

            } catch (Exception e) {
                e.getStackTrace();
            } finally {
                lock.unlock();
            }
        });

        //启动线程
        producer.start();
        Thread.sleep(1000);
        consumer.start();

//        producer.join();
//        consumer.join();
        Thread.currentThread().join();
    }
}
