package thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 * @Author: rj
 * @Date: 2020-11-19 17:16
 * @Version: 1.0
 */
public class SpinLockTest {
    public static void main(String[] args) {
        MyLock lock = new MyLock();
        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                lock.lock();
                try{
                    try{
                        TimeUnit.SECONDS.sleep(1);}catch (InterruptedException e) {e.printStackTrace();}
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            },"线程"+i).start();
        }
    }
}

class MyLock{
    // 现在的泛型装的是Thread，原子引用线程
    private AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock(){
        Thread thread = Thread.currentThread();
        System.out.println("线程："+thread.getName()+"进来了");
        // 开始自旋，期望值是null，更新值是当前线程，如果是null，则更新为当前线程，否者自旋
        while(!atomicReference.compareAndSet(null,thread)){

        }
        System.out.println("线程："+thread.getName()+"加锁");
    }

    public void unlock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println("线程："+thread.getName()+"释放锁");
    }
}
/*
线程：线程0进来了
线程：线程2进来了
线程：线程1进来了
线程：线程0加锁
线程：线程0释放锁
线程：线程1加锁
线程：线程1释放锁
线程：线程2加锁
线程：线程2释放锁*/
