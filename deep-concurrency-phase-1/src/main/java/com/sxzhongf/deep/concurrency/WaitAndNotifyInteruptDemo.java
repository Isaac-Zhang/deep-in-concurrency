package com.sxzhongf.deep.concurrency;

/**
 * 对象等待和唤醒,中断InterruptDEMO
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @since
 */
public class WaitAndNotifyInteruptDemo {
    private static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        //创建线程
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("----Begin-----");

                //阻塞当前线程
                synchronized (obj){
                    try {
                        obj.wait();
                        System.out.println("------end-------");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        threadA.start();

        Thread.sleep(1000);
        System.out.println("---Begin interrupt threadA----");
        //设置interrupt状态
        threadA.interrupt();
        System.out.println("---End interrupt threadA----");
    }
}
