package com.sxzhongf.deep.concurrency;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 使用{@code ReentrantReadWriteLock} 实现
 * 一个线程安全并且比{@link ReentrantLock}
 * 性能更高的List
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @since
 */
public class ReentrantReadWriteCustomizeListDemo {
    //非线程安全的list
    private ArrayList<String> arrayList = new ArrayList<>();
    //独占锁
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    /**
     * 添加元素
     *
     * @param element
     */
    public void add(String element) {
        writeLock.lock();
        try {
            arrayList.add(element);
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * 删除元素
     *
     * @param element
     */
    public void remove(String element) {
        writeLock.lock();
        try {
            arrayList.remove(element);
        } finally {
            writeLock.unlock();
        }
    }

    public String get(int index) {
        readLock.lock();
        try {
            return arrayList.get(index);
        } finally {
            readLock.unlock();
        }
    }
}
