package interview;

/**
 * @Author: rj
 * @Date: 2020-11-30 16:50
 * @Version: 1.0
 */
public class AutoIncrement {

    public static void main(String[] args) {
        Integer n = 125;
        String str = "hello";
        change(n,str);
        System.out.println(n); // 125
        System.out.println(str); // hello

        int i = 127;
        Integer j = 127;
        System.out.println(i == j);
        Integer l = new Integer(127);
        System.out.println(j == l); // false
    }
    public static void change(Integer n,String str){
        n += 1; // 相当于 new Integer(n+1)
        str += "world"; // 相当于 new String("helloworld")
    }
}
