package com.sxzhongf.deep.concurrency;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

/**
 * AtomicLong 统计0的个数
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @since
 */
public class AtomicLongDemo {
    private static AtomicLong atomicLong = new AtomicLong();

    private static Integer[] arrayOne = new Integer[]{0, 1, 2, 3, 0, 5, 6, 0, 56, 0};
    private static Integer[] arrayTwo = new Integer[]{10, 1, 2, 3, 0, 5, 6, 0, 56, 0};

    public static void main(String[] args) throws InterruptedException {
        Long startTime = System.currentTimeMillis();
        Thread thread1 = new Thread(() -> {
            int size = arrayOne.length;
            for (int i = 0; i < size; ++i) {
                if (arrayOne[i].intValue() == 0) {
                    atomicLong.incrementAndGet();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            int size = arrayTwo.length;
            for (int i = 0; i < size; ++i) {
                if (arrayTwo[i].intValue() == 0) {
                    atomicLong.incrementAndGet();
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        Long endTime = System.currentTimeMillis();

        System.out.println("count 0 : " + atomicLong + ",耗时 " + (endTime - startTime));
    }
}
