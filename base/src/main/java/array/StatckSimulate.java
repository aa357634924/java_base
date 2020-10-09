package array;

import java.util.Arrays;

/**
 * @Author: rj
 * @Date: 2020-10-09 23:15
 * @Version: 1.0
 * 使用一维数组，模拟栈数据结构
 *
 *  1.可以存储任意数据类型
 *  2.提供push方法可以进行压栈，栈满了有提示
 *  3.提供pop方法可以进行出栈，栈空了有提示
 *  4.编写测试用例
 */
public class StatckSimulate
{
    // 栈顶元素位置
    private static int position;
    // 数组，存放对象
    private Object[] objArr;

    public StatckSimulate(int length) {
        this.objArr = new Object[length];
    }

    public StatckSimulate() {
        this.objArr = new Object[16];
    }

    public void push(Object obj){
        if(position >= objArr.length){
            System.out.println("内存已满！！！");
            return;
        }
        objArr[position++] = obj;
    }

    public Object pop(){
        if(position <= 0){
            return "数据为空！！！";
        }
        Object obj = objArr[--position];
        objArr[position] = null;
        return obj;
    }

    @Override
    public String toString() {
        return "StatckSimulate{" +
                "objArr=" + Arrays.toString(objArr) +
                '}';
    }

    public static void main(String[] args) {
        StatckSimulate ss = new StatckSimulate(3);
        ss.push(1);
        ss.push(2);
        ss.push(3);
        System.out.println(ss); // StatckSimulate{objArr=[1, 2, 3]}
        ss.push(4);
        System.out.println(ss); // 内存已满！！！
        System.out.println(ss.pop());   // 3
        System.out.println(ss); // StatckSimulate{objArr=[1, 2, null]}
        System.out.println(ss.pop());   // 2
        System.out.println(ss); // StatckSimulate{objArr=[1, null, null]}
        System.out.println(ss.pop());   // 1
        System.out.println(ss); // StatckSimulate{objArr=[null, null, null]}
        System.out.println(ss.pop());   // 数据为空！！！
        System.out.println(ss); // StatckSimulate{objArr=[null, null, null]}
        ss.push(3);
        System.out.println(ss); // StatckSimulate{objArr=[1, 3, null]}

    }
}
