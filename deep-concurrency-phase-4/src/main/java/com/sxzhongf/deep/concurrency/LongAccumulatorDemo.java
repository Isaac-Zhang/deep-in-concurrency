package com.sxzhongf.deep.concurrency;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.function.LongBinaryOperator;

/**
 * {@link LongAccumulator} how to use
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @since
 */
public class LongAccumulatorDemo {
    private static LongAccumulator atomicLong = new LongAccumulator(new LongBinaryOperator() {
        @Override
        public long applyAsLong(long left, long right) {
            return left - right;
        }
    }, 0);

    private static Integer[] arrayOne = new Integer[]{0, 1, 2, 3, 0, 5, 6, 0, 56, 0};
    private static Integer[] arrayTwo = new Integer[]{10, 1, 2, 3, 0, 5, 6, 0, 56, 0};

    public static void main(String[] args) throws InterruptedException {
        Long startTime = System.currentTimeMillis();
        Thread thread1 = new Thread(() -> {
            int size = arrayOne.length;
            for (int i = 0; i < size; ++i) {
                //if (arrayOne[i].intValue() == 1) {
                atomicLong.accumulate(arrayOne[i].intValue());
                //}
            }
        });

        Thread thread2 = new Thread(() -> {
            int size = arrayTwo.length;
            for (int i = 0; i < size; ++i) {
                //if (arrayTwo[i].intValue() == 1) {
                atomicLong.accumulate(arrayTwo[i].intValue());
                //}
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