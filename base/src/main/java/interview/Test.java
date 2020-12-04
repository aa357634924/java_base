package interview;

/**
 * @Author: rj
 * @Date: 2020-12-03 12:30
 * @Version: 1.0
 */
public abstract class Test {
    public Test(){
        System.out.println("abstract class");
    }
//    private static Test t1 = new Test();
    {
        System.out.println("BlockA");
    }
    static{
        System.out.println("BlockB");
    }

    public abstract void t();

    public static void main(String[] args) {
//        Test t2 = new Test();
        System.out.println("----- 验证 String 每次操作都是 new 了一个新对象 ------");
        String str1 = "123";
        System.out.println(System.identityHashCode(str1)); // 1872034366
        str1 += "333";
        System.out.println(System.identityHashCode(str1)); // 1581781576
        System.out.println("----- 验证 StringBuilder 操作的是同一个对象------");
        StringBuilder str = new StringBuilder("123");
        System.out.println(System.identityHashCode(str)); // 644117698
        str.append("333");
        System.out.println(System.identityHashCode(str)); // 644117698


    }
}

