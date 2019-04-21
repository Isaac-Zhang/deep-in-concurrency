package com.sxzhongf.deep.concurrency;

/**
 * how to use {@link ThreadLocal}
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @since
 */
public class ThreadLocalDemo {
    //创建ThreadLocal 变量
    static ThreadLocal<String> localVariable = new ThreadLocal<>();
    //Demo 2 创建线程变量
    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
//        threadLocalVariable();
        threadLocal.set("hello world.");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("child threadlocal variable : "+threadLocal.get());
            }
        });

        thread.start();

        System.out.println("main threadlocal variable : "+threadLocal.get());

    }

    /**
     * the place of ThreadLocal variable saved
     */
    private static void threadLocalVariable() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                //设置线程1中本地变量值
                localVariable.set("thread1 local varibale");
                //调用打印
                print("thread1");
                //打印本地变量值
                System.out.println("thread1 remove after : " + localVariable.get());
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                localVariable.set("thread2 local variable");
                print("thread2");
                System.out.println("thread2 remove after : " + localVariable.get());
            }
        });

        thread1.start();
        thread2.start();
    }

    static void print(String str) {
        //打印当前线程本地内存中的ThreadLocal变量
        System.out.println(str + " : ThreadLocal 本地副本value : " + localVariable.get());
        //清除当前线程本地内存中的变量
        //localVariable.remove();
    }
}

