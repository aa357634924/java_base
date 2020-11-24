package singleton;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO
 * 单例模式
 *
 * @author rj
 * @version 1.0
 * @date 2020-11-12 12:46
 */
public class SingleTon5 {
    // volatile 保证内存可见性，禁止指令重排序
    private static volatile SingleTon5 singleTon;

    /**
     * 构造函数私有化，防止其他类创建该对象
     */
    private SingleTon5() {
    }

    // DCL （double check lock） 双端检索机制
    public static SingleTon5 getInstance() {
        if (null == singleTon) {
            synchronized (SingleTon5.class) {
                if (null == singleTon) {
                    singleTon = new SingleTon5();
                }
            }
        }
        return singleTon;
    }

    public static void main(String[] args) {
        SingleTon5 instance = SingleTon5.getInstance();
        AtomicInteger a = new AtomicInteger();
        a.addAndGet(1);
        a.getAndIncrement();
    }
}
