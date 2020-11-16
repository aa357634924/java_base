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
        return Thread.currentThread().getName();
    }
}
