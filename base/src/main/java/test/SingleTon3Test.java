package test;

import singleton.SingleTon3;

import java.util.concurrent.*;

/**
 * @Author: rj
 * @Date: 2020-10-20 16:44
 * @Version: 1.0
 */
public class SingleTon3Test {
    public static void main(String[] args) {
        Callable<SingleTon3> c1 = new Callable() {
            @Override
            public SingleTon3 call() throws Exception {
                return SingleTon3.getInstance();
            }
        };
        ExecutorService ex = Executors.newFixedThreadPool(2);
        Future<SingleTon3> submit1 = ex.submit(c1);
        Future<SingleTon3> submit2 = ex.submit(c1);
        try {
            SingleTon3 singleTon1 = submit1.get();
            SingleTon3 singleTon2 = submit2.get();
            System.out.println(singleTon1 == singleTon2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
