package thread;

import java.util.concurrent.TimeUnit;

/**
 * TODO
 * 验证volatile保证内存可见性
 *
 * @author rj
 * @version 1.0
 * @date 2020-11-13 21:47
 */
public class VerifyVolatile {
    //实例变量
    volatile int a;

    public void add() {
        this.a = 60;
    }

    public static void main(String[] args) {
        VerifyVolatile verifyVolatile = new VerifyVolatile();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            verifyVolatile.add();
            System.out.println(Thread.currentThread().getName() + "执行结束" + "," + verifyVolatile.a);
        }, "aaa").start();

        while (verifyVolatile.a == 0) {

        }
        System.out.println("main线程结束");
    }
}
