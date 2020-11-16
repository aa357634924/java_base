package thread;

import java.util.concurrent.atomic.AtomicReference;

/**
 * TODO
 *  使用AtomicReference 解决CAS操作引起的ABA问题
 * @author rj
 * @version 1.0
 * @date 2020-11-14 14:13
 */
public class AtomicReferenceDemo {

    public static void main(String[] args) {
        User1 user1 = new User1(1, "张三");
        User1 user2 = new User1(2, "李四");
        AtomicReference<User1> atomicReference = new AtomicReference<>();
        // 设置主物理内存值
        atomicReference.set(user1);
        System.out.println(atomicReference.compareAndSet(user1, user2) + "---" + atomicReference.get().toString()); // true---thread.User@4b67cf4d
        System.out.println(atomicReference.compareAndSet(user1, user2) + "---" + atomicReference.get().toString()); // false---thread.User@4b67cf4d
    }
}

class User1 {
    private int id;
    private String name;

    public User1(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
