package com.sxzhongf.deep.concurrency;

import sun.misc.Unsafe;

/**
 * TODO
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @since
 */
public class UnsafeClassDemo {
    // 获取Unsafe 实例
    /**
     * 由于getUnsafe 会校验调用的ClassLoad必须为Bootstrap ClassLoad
     * 很明显，我们使用main函数调用，为AppClassLoad,会直接抛出unsafe异常
     */
    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private static final long stateOffset;
    private volatile long state = 0;

    static {
        try{
            //获取state变量在类 UnsafeClassDemo中的偏移量
            stateOffset = unsafe.objectFieldOffset(UnsafeClassDemo.class.getDeclaredField("state"));
        }
        catch(Exception e){
            System.out.println(e.getLocalizedMessage());
            throw new Error(e);
        }
    }
    public static void main(String[] args) {
        //创建实例，并设置state 值为1
        UnsafeClassDemo demo = new UnsafeClassDemo();
        Boolean success = unsafe.compareAndSwapLong(demo,stateOffset,0,1);
        System.out.println(success);
    }
}
