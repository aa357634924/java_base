package thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 计数器
 * @Author: rj
 * @Date: 2020-11-19 17:38
 * @Version: 1.0
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                try{
                    TimeUnit.SECONDS.sleep(3);}catch (InterruptedException e) {e.printStackTrace();}
                System.out.println(Thread.currentThread().getName()+"执行完毕");
                countDownLatch.countDown();
            },"线程"+i).start();
        }
        try {
            System.out.println("主线程开始等待。。。");
            countDownLatch.await();
            System.out.println("全部执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
