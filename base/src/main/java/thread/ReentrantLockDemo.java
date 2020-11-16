package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO
 * 模拟生产者消费者
 * 一个线程执行生产操作，一个线程执行消费操作，
 * 库存没有了生产者生产，消费者等待；库存满时，生产者等待，消费者消费
 * 库存总量设置为1，初始为0
 *
 * @author rj
 * @version 1.0
 * @date 2020-11-14 22:27
 */
public class ReentrantLockDemo {
    // 库存
    private volatile int num;
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void create() {
        lock.lock();
        try {
            // 库存不够时生产
            while (num != 0) {
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName() + "生产，库存总量为：" + num);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void take() {
        lock.lock();
        try {
            // 库存不够时生产
            while (num != 1) {
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + "消费，库存总量为：" + num);
            condition.signalAll();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                reentrantLockDemo.create();
            }, "生产者" + i).start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                reentrantLockDemo.take();
            }, "消费者" + i).start();
        }
    }
}
/*
生产者0生产，库存总量为：1
消费者0消费，库存总量为：0
生产者1生产，库存总量为：1
消费者2消费，库存总量为：0
生产者2生产，库存总量为：1
消费者1消费，库存总量为：0
生产者3生产，库存总量为：1
消费者4消费，库存总量为：0
生产者4生产，库存总量为：1
消费者3消费，库存总量为：0*/
