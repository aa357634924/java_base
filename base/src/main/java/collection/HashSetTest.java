package collection;

import java.util.*;

/**
 * @Author: rj
 * @Date: 2020-11-18 12:04
 * @Version: 1.0
 */
public class HashSetTest {
    public static void main(String[] args) {
        HashSet set = new HashSet<>();
        Integer int1 = 128;
        Integer int2 = 128;
        set.add(int1);
        set.add(int2);
        System.out.println(int1 == int2); // -128~127 之外的均使用new Integer(n)创建
        System.out.println(System.identityHashCode(int1)); // 打印Integer的内存地址  460141958
        System.out.println(System.identityHashCode(int2)); //  1163157884
        // 若存在相同的值，则拒绝后入者
        for (Object obj:set) {
            System.out.println(System.identityHashCode(obj)); // 460141958
        }
        TreeSet t = new TreeSet<>();
        Map map = new HashMap<>();
        map.put(null,"12123");
        System.out.println(map.get(null)); // 12123
    }
}