package test;

import singleton.SingleTon2;

/**
 * @Author: rj
 * @Date: 2020-10-20 16:06
 * @Version: 1.0
 */
public class Test20201020 {
    public static void main(String[] args) {
        SingleTon2 singleTon2 = SingleTon2.singleTon;
        System.out.println(singleTon2);
        SingleTon2 singleTon21 = new SingleTon2("haha");
        System.out.println(singleTon21);
    }
}
