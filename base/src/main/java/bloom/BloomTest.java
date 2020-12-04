package bloom;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * 布隆过滤器  测试100万条数据查询10000个数据是否存在的查询时间及误差量
 *
 * @Author: rj
 * @Date: 2020-11-24 21:43
 * @Version: 1.0
 */
public class BloomTest {

    public static void main(String[] args) {
        int size = 1000000;//预计要插入多少数据
        double fpp = 0.0001;//期望的误判率
        // Funnels.integerFunnel() 传入的数据是int类型
        BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size, fpp);
        // 插入1000000条数据
        for (int i = 0; i < 1000000; i++) {
            bloomFilter.put(i);
        }
        // 查询10000条不在bit数组中的数据
        int count = 0;
        long start = System.nanoTime();
        System.out.println();
        for (int i = size; i < size + 1000000; i++) {
            if (bloomFilter.mightContain(i)) {
                count++;
                System.out.println(i + "误判了");
            }
        }
        long end = System.nanoTime();
        System.out.println("总共的误判数:" + count); // 102
        System.out.println("总执行时间：" + (end - start)); // 151651753
    }
}
