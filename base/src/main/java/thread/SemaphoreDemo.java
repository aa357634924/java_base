package thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 * 信号量，多个线程同时争抢多个共享资源，适合复杂场景使用，如秒杀系统
 *
 * @author rj
 * @version 1.0
 * @date 2020-11-14 20:31
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        // 可支持3个线程同时操作
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "获取到资源");
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "释放资源");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, "线程" + i).start();
        }
    }
}
/*
线程0获取到资源
线程2获取到资源
线程1获取到资源
线程1释放资源
线程2释放资源
线程3获取到资源
线程0释放资源
线程4获取到资源
线程5获取到资源
线程3释放资源
线程4释放资源
线程5释放资源*/