package com.sxzhongf.deep.concurrency;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用{@code ReentrantLock} 实现
 * 一个线程安全的List
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @since
 */
public class ReentrantLockCustomizeListDemo {
    //非线程安全的list
    private ArrayList<String> arrayList = new ArrayList<>();
    //独占锁
    private volatile ReentrantLock lock = new ReentrantLock();

    /**
     * 添加元素
     *
     * @param element
     */
    public void add(String element) {
        lock.lock();
        try {
            arrayList.add(element);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 删除元素
     *
     * @param element
     */
    public void remove(String element) {
        lock.lock();
        try {
            arrayList.remove(element);
        } finally {
            lock.unlock();
        }
    }

    public String get(int index) {
        lock.lock();
        try {
            return arrayList.get(index);
        } finally {
            lock.unlock();
        }
    }
}
