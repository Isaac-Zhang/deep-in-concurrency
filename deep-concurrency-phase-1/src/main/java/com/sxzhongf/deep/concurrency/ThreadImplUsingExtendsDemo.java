package com.sxzhongf.deep.concurrency;

/**
 * 使用继承方式实现线程
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @since
 */
public class ThreadImplUsingExtendsDemo extends Thread{

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        System.out.printf("this is a main thread, name's [%s] \n",Thread.currentThread().getName());
    }

    /**
     * 好处：可直接使用this获取当前thread，方便传参
     * 缺点：1. Java 不支持多继承
     *       2. 任务和代码没有分离
     *       3. 任务没有返回值
     */
    public static class MyThread extends Thread {
        @Override
        public void run(){
            System.out.printf("this is a child thread, name's [%s] \n",this.getName());
        }
    }
}
