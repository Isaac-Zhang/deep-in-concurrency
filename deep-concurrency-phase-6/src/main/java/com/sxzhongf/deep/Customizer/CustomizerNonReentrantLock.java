package com.sxzhongf.deep.Customizer;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 自定义实现不可重入的独占锁
 * {@link ReentrantLock}
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang</a>
 * @since
 */
public class CustomizerNonReentrantLock implements Lock, Serializable {

    //内部帮助类
    private static class Sync extends AbstractQueuedSynchronizer {

        /**
         * 判断是否已经被线程持有
         *
         * @return 判断结果
         */
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        /**
         * 如果state为0，尝试获取锁
         *
         * @param acquire
         * @return
         */
        public boolean tryAcquire(int acquire) {
            assert acquire == 1;
            if (compareAndSetState(0, 1)) {
                Thread currentThread = Thread.currentThread();
                setExclusiveOwnerThread(currentThread);
                return true;
            }
            return false;
        }

        /**
         * 尝试释放锁，并设置state为0
         *
         * @param release
         * @return
         */
        public boolean tryRelease(int release) {
            assert release == 1;
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return false;
        }

        Condition newCondition() {
            return new ConditionObject();
        }
    }

    //创建一个具体的Sync类来承担具体的工作
    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    public boolean isLocked() {
        return sync.isHeldExclusively();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }
}
