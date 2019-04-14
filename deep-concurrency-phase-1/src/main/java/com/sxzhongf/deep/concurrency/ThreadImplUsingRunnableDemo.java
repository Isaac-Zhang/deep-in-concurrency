package com.sxzhongf.deep.concurrency;

/**
 * 使用{@link Runnable} 方式实现线程
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @since
 */
public class ThreadImplUsingRunnableDemo {

    public static void main(String[] args) {
        RunnableTask task = new RunnableTask();
        new Thread(task).start();
        new Thread(task).start();
        new Thread(task).start();

        System.out.printf("this is a main thread, name's [%s] \n",Thread.currentThread().getName());
    }

    /**
     * 好处：代码和业务可分离，在多线程实现时方便
     * 缺点：1. 任务没有返回值
     *       2. 需要传参只能通过在主线程声明final变量
     */
    public static class RunnableTask implements Runnable {

        /**
         *  模拟业务代码
         */
        @Override
        public void run(){
            System.out.printf("this is a child thread, name's [%s] \n",Thread.currentThread().getName());
        }
    }
}
