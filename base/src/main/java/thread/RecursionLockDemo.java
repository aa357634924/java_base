package thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * TODO
 * 模拟实现自旋锁（递归锁）
 *
 * @author rj
 * @version 1.0
 * @date 2020-11-14 19:46
 */
public class RecursionLockDemo {

    private AtomicReference<Thread> reference = new AtomicReference<>();

    public void lock() {
        while (!reference.compareAndSet(null, Thread.currentThread())) {

        }
        System.out.println(Thread.currentThread().getName() + "获取锁成功");
    }

    public void unLock() {
        while (!reference.compareAndSet(Thread.currentThread(), null)) {

        }
        System.out.println(Thread.currentThread().getName() + "释放锁成功");
    }

    public static void main(String[] args) {
        RecursionLockDemo lock = new RecursionLockDemo();
        new Thread(() -> {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unLock();
        }, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            lock.lock();
            lock.unLock();
        }, "t2").start();

    }
}
