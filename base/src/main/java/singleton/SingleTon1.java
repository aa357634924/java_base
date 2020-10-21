package singleton;

/**
 * @Author: rj
 * @Date: 2020-10-17 14:00
 * @Version: 1.0
 * 饿汉式：
 *  直接创建对象
 */
public class SingleTon1 {
    public static final SingleTon1 INSTANCE = new SingleTon1();
    private SingleTon1(){

    }
}
