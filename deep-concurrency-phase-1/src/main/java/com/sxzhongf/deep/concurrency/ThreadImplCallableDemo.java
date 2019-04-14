package com.sxzhongf.deep.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 使用{@link Callable}方式实现线程
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @since
 */
public class ThreadImplCallableDemo {
    public static void main(String[] args) {

        //创建异步任务
        FutureTask<String> task = new FutureTask<String>(new CallerTask());
        //启动线程
        new Thread(task).start();
        try {
            //等待线程执行完毕，返回结果
            String result = task.get();
            System.err.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.printf("this is a main thread, name's [%s] \n", Thread.currentThread().getName());
    }

    /**
     * 好处：
     *      1. 业务代码分离
     *      2. 带有结果返回值
     *
     * 缺点：任务没有返回值
     */
    public static class CallerTask implements Callable<String> {
        public String call() throws Exception {
            return String.format("this is a child thread, name's [%s] \n",
                    Thread.currentThread().getName());
        }
    }
}
