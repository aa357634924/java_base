package thread;

import java.util.concurrent.TimeUnit;

/**
 * TODO
 * 两个线程交替打印,一个线程打印A~Z，另一个线程打印1~26
 * 如：A1B2C3...
 *
 * @author rj
 * @version 1.0
 * @date 2020-11-13 17:38
 */
public class PoolPrintThread {

    public static void main(String[] args) {
        PoolPrintThread p = new PoolPrintThread();
        Thread t1 = new Thread(() -> {
            synchronized (p) {
                for (int i = 1; i <= 26; i++) {
                    System.out.print(i);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        p.notify();
                        if (i == 26) {
                            break;
                        }
                        p.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (p) {
                for (int i = 1; i <= 26; i++) {
                    System.out.print(Character.toUpperCase((char) (96 + i)));//大写
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        p.notify();
                        if (i == 26) {
                            break;
                        }
                        p.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.start();
        t2.start();
    }
}
