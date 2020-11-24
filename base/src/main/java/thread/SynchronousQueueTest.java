package thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 同步阻塞队列，消费一次就必须生产一次，生产一次就必须消费一次，轮询执行
 * 生产者消费者模式
 * @Author: rj
 * @Date: 2020-11-19 19:39
 * @Version: 1.0
 */
public class SynchronousQueueTest {
    public static void main(String[] args) {
        BlockingQueue queue = new SynchronousQueue();
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"执行生产1");
                queue.put("1");
                System.out.println(Thread.currentThread().getName()+"执行生产2");
                queue.put("2");
                System.out.println(Thread.currentThread().getName()+"执行生产3");
                queue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"线程A").start();
        try{
            TimeUnit.SECONDS.sleep(1);}catch (InterruptedException e) {e.printStackTrace();}
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"执行消费:"+queue.take());
                System.out.println(Thread.currentThread().getName()+"执行消费:"+queue.take());
                System.out.println(Thread.currentThread().getName()+"执行消费:"+queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"线程B").start();
    }
}
