package test;

/**
 * @Author: rj
 * @Date: 2020-10-17 13:40
 * @Version: 1.0
 */
public class Test20201017 {
    public static void main(String[] args) {
        int i = 1;
        i = i++;
        int j = i++;
        int k = i + ++i * i++;
        System.out.println(i);
        System.out.println(j);
        System.out.println(k);
    }
}
