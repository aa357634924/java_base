package thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 验证阻塞队列
 * @Author: rj
 * @Date: 2020-11-19 19:23
 * @Version: 1.0
 */
public class BlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(1);
        System.out.println("---------- offer 插入元素-----------");
        //使用offer插入的时候，需要指定时间，如果2秒还没有插入，那么就放弃插入；返回true/false
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("b", 2L, TimeUnit.SECONDS));
        System.out.println("---------- poll 取出元素-----------");
        // 返回元素或者null
        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2L, TimeUnit.SECONDS));
    }
}
