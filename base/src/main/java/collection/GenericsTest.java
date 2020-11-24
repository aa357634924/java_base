package collection;

/**
 * 泛型
 * @Author: rj
 * @Date: 2020-11-18 11:53
 * @Version: 1.0
 */
public class GenericsTest {
    public static void main(String[] args) {
        MyGenerics<String> myGenerics = new MyGenerics();
        Object test = myGenerics.test("hello");
        System.out.println(test);// hello

        MyGenerics<Integer> myGenerics11 = new MyGenerics();
        Object test11 = myGenerics11.test(123);
        System.out.println(test11);// 123
    }
}
class MyGenerics<T>{
    public MyGenerics(){}

    public <T> void test(){

    }

    public <T> T test(T t){
        return t;
    }
}
