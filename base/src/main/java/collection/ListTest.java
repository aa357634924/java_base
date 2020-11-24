package collection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author: rj
 * @Date: 2020-11-18 9:09
 * @Version: 1.0
 */
public class ListTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            String next = iterator.next();
            System.out.println("当前迭代的值是："+next);
            // list.remove(next); // Exception in thread "main" java.util.ConcurrentModificationException
            iterator.remove(); // 使用迭代器的remove方法，可以正常删除
        }
        System.out.println("集合长度为："+list.size());
//        list = null; // 如果list为null，则跑出java.lang.NullPointerException
        for (Object obj:list) { // 如果list不为null，并且size为0，则直接跳过，不会抛异常
            System.out.println((String)obj);
        }

        // binarySearch 二分法查找元素在集合中的位置（前提是list是一个有序集合）
        List list1 = new ArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        Collections.sort(list1);
        int i = Collections.binarySearch(list1, 2);
        System.out.println("元素所在位置："+i);
    }
}
