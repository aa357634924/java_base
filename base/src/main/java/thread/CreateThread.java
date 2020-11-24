package thread;

import java.util.concurrent.*;

/**
 * TODO
 * 多线程创建方式
 * @author rj
 * @version 1.0
 * @date 2020-11-10 16:58
 */
public class CreateThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thead1 t1 = new Thead1();
        Thread t2 = new Thread(new Thread2());
        FutureTask<String> stringFutureTask = new FutureTask<>(new Thread3());
        Thread t3 = new Thread(stringFutureTask);
        t1.start();
        t2.start();
        t3.start();
        System.out.println(stringFutureTask.get());
        System.out.println("-------------");
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<?> submit = executorService.submit(new Thread3());
        System.out.println(submit.get());
        executorService.shutdown();
        System.out.println("-------------");
        // 多个线程执行 一个FutureTask的时候，call方法只会执行一次
        FutureTask<String> futureTask = new FutureTask<>(new Thread3());
        new Thread(futureTask, "AAA").start();
        new Thread(futureTask, "BBB").start();
        /* 执行结果
        Thread-0
        Thread-2进来了
        Thread-1
        Thread-2
        -------------
        pool-1-thread-1进来了
        pool-1-thread-1
        -------------
        AAA进来了*/
    }
}

class Thead1 extends Thread{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}

class Thread2 implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}

class Thread3 implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"进来了");
        return Thread.currentThread().getName();
    }
}
