package thread;

/**
 * @Author: rj
 * @Date: 2020-11-23 23:28
 * @Version: 1.0
 */
public class LockTest {

    public static void main(String[] args) {
        lockDisapper();
        lockCoarsing();
        return;
    }

    // 锁粗化
    public static StringBuffer lockCoarsing() {
        StringBuffer sb = new StringBuffer();
        sb.append(1).append(2).append(3);
        return sb;
    }

    // 锁消除 锁资源生命周期在方法内部有效
    public static void lockDisapper() {
        StringBuffer sb = new StringBuffer();
        sb.append(1).append(2).append(3);
    }


}
