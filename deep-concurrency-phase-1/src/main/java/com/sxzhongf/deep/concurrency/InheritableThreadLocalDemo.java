package com.sxzhongf.deep.concurrency;

/**
 * TODO
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @since
 */
public class InheritableThreadLocalDemo {

    //创建线程变量
    public static ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

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
}
