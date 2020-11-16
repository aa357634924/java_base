package thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO
 *  测试守护线程
 * @author rj
 * @version 1.0
 * @date 2020-11-11 12:38
 */
public class DeamonThreadTest {
    public static void main(String[] args) {
        God god = new God();
        User user = new User();
        Thread t1 = new Thread(god);
        t1.setDaemon(true);
        Thread t2 = new Thread(user);
        t1.start();
        t2.start();
    }
}

class God implements Runnable{

    @Override
    public void run() {
        while (true){
            System.out.println("守护线程");
        }
    }
}

class User implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("用户线程。。。。。。");
        }
    }
}
