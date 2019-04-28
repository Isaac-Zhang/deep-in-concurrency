package com.sxzhongf.deep.concurrency;

import java.util.concurrent.ThreadLocalRandom;

/**
 * TODO
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @since
 */
public class ThreadLocalRandomDemo {
    public static void main(String[] args) {
        //获取随机数生成器
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (int i = 0; i < 10; ++i) {
            System.out.println(random.nextInt(5));
        }
    }
}
