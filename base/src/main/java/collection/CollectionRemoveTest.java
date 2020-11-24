package collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @Author: rj
 * @Date: 2020-11-18 10:33
 * @Version: 1.0
 */
public class CollectionRemoveTest {
    public static void main(String[] args) {
        // 非new生成的Integer，在-128~127之间，指向的是同一个地址（java常量池），其他的数值范围都是在堆中重新new出来的
        Integer i1 = 128;
        Integer i2 = 128;
        System.out.println(i1 == i2); // false
        Integer i3 = 127;
        Integer i4 = 127;
        System.out.println(i3 == i4); // true
        Vector v = new Vector();
        v.add(1);
    }
}
