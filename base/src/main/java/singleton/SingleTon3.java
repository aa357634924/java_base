package singleton;

/**
 * @Author: rj
 * @Date: 2020-10-20 16:35
 * @Version: 1.0
 * 懒汉式：
 * 延迟创建实例对象
 */
public class SingleTon3 {
    private static SingleTon3 INSTANCE;

    public static SingleTon3 getInstance() {
        if (INSTANCE == null) {
            synchronized (SingleTon3.class) {
                if (INSTANCE == null) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new SingleTon3();
                }
            }
        }
        return INSTANCE;
    }

    public SingleTon3() {
    }
}
