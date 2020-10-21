package test;

import singleton.SingleTon4;

/**
 * @Author: rj
 * @Date: 2020-10-20 18:14
 * @Version: 1.0
 */
public class SingleTon4Test {
    public static void main(String[] args) {
        SingleTon4 singleTon4 = new SingleTon4();
        System.out.println("-------");
        SingleTon4.getInstance();
    }
}
