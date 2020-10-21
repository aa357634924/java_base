package singleton;

/**
 * @Author: rj
 * @Date: 2020-10-20 18:08
 * @Version: 1.0
 * 懒汉式：
 * 延迟创建实例对象
 * 内部类方式
 * 静态内部类不会自动随着外部类的加载和初始化而初始化，它是调用的时候才会单独进行加载和初始化
 */
public class SingleTon4 {
    public SingleTon4() {
    }

    private static class Inner {
        private static SingleTon4 INSTANCE = new SingleTon4();
        static {
            System.out.println("加载内部类");
        }
    }

    public static SingleTon4 getInstance() {
        return Inner.INSTANCE;
    }
}
