package com.sxzhongf.deep.concurrency;

import java.util.concurrent.atomic.LongAdder;

/**
 * {@link LongAdder} demo
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @since
 */
public class LongAdderDemo {
    private static LongAdder atomicLong = new LongAdder();

    private static Integer[] arrayOne = new Integer[]{0, 1, 2, 3, 0, 5, 6, 0, 56, 0};
    private static Integer[] arrayTwo = new Integer[]{10, 1, 2, 3, 0, 5, 6, 0, 56, 0};

    public static void main(String[] args) throws InterruptedException {
        Long startTime = System.currentTimeMillis();
        Thread thread1 = new Thread(() -> {
            int size = arrayOne.length;
            for (int i = 0; i < size; ++i) {
                if (arrayOne[i].intValue() == 0) {
                    atomicLong.increment();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            int size = arrayTwo.length;
            for (int i = 0; i < size; ++i) {
                if (arrayTwo[i].intValue() == 0) {
                    atomicLong.increment();
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
