package thread;

import java.util.HashSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * TODO
 * 原子引用+版本号  解决 ABA 问题
 *
 * @author rj
 * @version 1.0
 * @date 2020-11-14 14:42
 */
public class AtomicStampedReferenceDemo {
    public static void main(String[] args) {
        AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

        System.out.println("################ ABA问题的产生 ################");
        new Thread(() -> {
            atomicReference.compareAndSet(100, 2);
            atomicReference.compareAndSet(2, 100);
        }, "t1").start();
        new Thread(() -> {
            // 休眠1秒，保证上一个线程完成ABA操作
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "----" + atomicReference.compareAndSet(100, 3) + "---" + atomicReference.get());
        }, "t2").start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("################ 以下ABA问题的解决 ################");
        // 设置内存值和初始版本号
        new Thread(() -> {
            int stamped = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "第一次版本号：" + stamped);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100, 2, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "第二次版本号：" + atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(2, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "第三次版本号：" + atomicStampedReference.getStamp());
        }, "t3").start();
        new Thread(() -> {
            int stamped = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "第一次版本号：" + stamped);
            // 休眠1秒，保证上一个线程完成ABA操作
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean bool = atomicStampedReference.compareAndSet(100, 2, stamped, stamped + 1);
            System.out.println(Thread.currentThread().getName() + "操作结果：" + bool + "----" + "最后的值为：" + atomicStampedReference.getReference());
        }, "t4").start();
        CopyOnWriteArrayList<Object> objects = new CopyOnWriteArrayList<>();
        objects.get(0);
        HashSet<Object> set = new HashSet<>();
        set.add(1);
    }

    public synchronized void test1(){
        test2(); // 可以直接使用test1的锁
    }

    public synchronized void test2(){

    }
}
//        ################ ABA问题的产生 ################
//        t2----true---3
//        ################ 以下ABA问题的解决 ################
//        t3第一次版本号：1
//        t4第一次版本号：1
//        t3第二次版本号：2
//        t3第三次版本号：3
//        t4操作结果：false----最后的值为：100
