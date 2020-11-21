package face;

public class StringTest {
    public static void main(String[] args) {
        String s = new String("2");
        s.intern();
        String s2 = "2";
        System.out.println(s == s2); // false

        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4); // true

        System.out.println("-----------------------");
        System.out.println();
        String str2 = new String("计算机技术");
        System.out.println(str2 == str2.intern());

        String str1 = new StringBuilder("计算机").append("技术").toString();
        System.out.println(str1 == str2);
        System.out.println(str1.intern() == str2.intern());

        System.out.println("------------------");
        // new Builder将堆中的引用地址 复制给了 字符串常量池 ，所以两个引用地址是一样的
        String str3 = new StringBuilder("计算机").append("的道路").toString();
        System.out.println(str3 == str3.intern()); // true

        // new String调用intern，字符串常量池中创建一个新的字符串并返回地址，str4指向堆中的引用地址
        String str4 = new String("测试new String intern的情况");
        System.out.println(str4 == str4.intern()); // false

    }
}
