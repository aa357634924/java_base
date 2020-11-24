package thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 * 循环屏障，达到指定的线程数，才会往下继续执行，否则被阻塞
 *
 * @author rj
 * @version 1.0
 * @date 2020-11-14 20:18
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
            System.out.println("线程数已达指定数量，等本方法执行完毕即可开始放行");
        });
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "已到达");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "继续执行");
            }, String.valueOf(i)).start();
        }
    }
}
/*0已到达
1已到达
2已到达
3已到达
4已到达
线程数已达指定数量，开始放行
4继续执行
0继续执行
1继续执行
2继续执行
3继续执行*/
