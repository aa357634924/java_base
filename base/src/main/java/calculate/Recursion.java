package calculate;

/**
 * @Author: rj
 * @Date: 2020-10-11 10:47
 * @Version: 1.0
 * 递归
 */
public class Recursion {
    /*
     * n步台阶，一次只能上1步或者2步，一共有几种走法
     * 1阶：1种，2阶：2种
     * */
    public int step(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("台阶数不能小于1");
        }
        if (n < 3) {
            return n;
        }
        return step(n - 1) + step(n - 2);
    }

    /**
     * 求N的阶乘
     */
    public int jieCheng(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * jieCheng(n - 1);
    }

    /**
     * 从第三个数开始，后一个数是前两个数的和,求第N个数的值
     * 如：1，1，2，3，5，8，13，21...
     */
    public int feiBoNaQie(int n) {
        if (n < 3) {
            return 1;
        }

        return feiBoNaQie(n - 1) + feiBoNaQie(n - 2);
    }

    public static void main(String[] args) {
        Recursion r = new Recursion();
//        int n = r.jieCheng(3);
//        System.out.println(n);
//        int n1 = r.feiBoNaQie(6);
//        System.out.println(n1);
        // n步台阶测试
        int n2 = r.step(0);
        System.out.println(n2);
    }
}
