package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO
 * 多线程之间顺序调用，A->B->C，要求如下：
 * A打印5次，B打印10次，C打印15次，
 * 紧接着
 * A打印5次，B打印10次，C打印15次，
 * 。。。。。。
 * 轮询打印10次
 *
 * @author rj
 * @version 1.0
 * @date 2020-11-15 10:28
 */
public class ReentrantLockDemo2 {
    private int num = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print5() throws InterruptedException {
        lock.lock();
        try {
            while (num != 1) {
                condition1.await();
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "打印" + i);
            }
            num = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() throws InterruptedException {
        lock.lock();
        try {
            while (num != 2) {
                condition2.await();
            }
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "打印" + i);
            }
            num = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() throws InterruptedException {
        lock.lock();
        try {
            while (num != 3) {
                condition3.await();
            }
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "打印" + i);
            }
            num = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo2 reentrantLockDemo2 = new ReentrantLockDemo2();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    reentrantLockDemo2.print5();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程A").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    reentrantLockDemo2.print10();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程B").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    reentrantLockDemo2.print15();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程C").start();
        new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        });
        Executors.newFixedThreadPool(1);
    }
}
