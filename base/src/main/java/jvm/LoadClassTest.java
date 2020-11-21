package jvm;

/**
 * 验证类的加载顺序
 * */
public class LoadClassTest {
    public static void main(String[] args) {
        Son son = new Son();
        System.out.println("第二次加载=====================");
        Son son1 = new Son();
    }
}

class Father{
    private static int n;
    private int m;
    static{
        System.out.println("父类的静态代码块");
    }
    public Father(){
        System.out.println("父类的构造方法");
    }
    {
        System.out.println("父类的普通代码块");
    }
}

class Son extends Father{
    private static int n;
    private int m;
    static{
        System.out.println("子类的静态代码块");
    }
    public Son(){
        //super和this不能同时使用
        // 子类的构造方法内第一行必须是super()，且只能super一次；
        super();
        System.out.println("子类的构造方法");
    }
    {
        System.out.println("子类的普通代码块");
    }
}
