package singleton;

import java.io.IOException;
import java.util.Properties;

/**
 * @Author: rj
 * @Date: 2020-10-20 15:50
 * @Version: 1.0
 * 饿汉式：
 *  静态代码块（读取初始化数据时使用）
 */
public class SingleTon2 {
    public static final SingleTon2 singleTon;
    private String name;

    static{
        Properties pro = new Properties();
        try {
            pro.load(SingleTon2.class.getClassLoader().getResourceAsStream("info.properties"));
            singleTon = new SingleTon2(pro.getProperty("name"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public SingleTon2() {
    }

    public SingleTon2(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SingleTon2{" +
                "name='" + name + '\'' +
                '}';
    }
}
