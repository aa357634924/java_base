package thread;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 手动创建线程池
 * @Author: rj
 * @Date: 2020-11-20 11:28
 * @Version: 1.0
 */
public class MyThreadPoolExecutor {
    public static void main(String[] args) {
        int corePoolSize = 2;
        int maximumPoolSize = 5;
        long keepAliveTime = 2L;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy() // 线程池默认拒绝策略，抛出异常：java.util.concurrent.RejectedExecutionException
//                new ThreadPoolExecutor.CallerRunsPolicy() // 返回给请求发起者执行
//                new ThreadPoolExecutor.DiscardPolicy() // 丢弃后到的任务
//                new ThreadPoolExecutor.DiscardOldestPolicy() // 丢弃队列中排队时间最长的任务
        );
        try{
            for (int i = 0; i < 9; i++) {
                final int tempInt = i;
                threadPoolExecutor.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"执行了,"+"tempInt="+tempInt);
                });
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            threadPoolExecutor.shutdown();
        }
    }
}
