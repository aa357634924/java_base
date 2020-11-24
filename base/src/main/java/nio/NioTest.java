package nio;

import java.nio.ByteBuffer;

/**
 * NIO 缓冲区 操作示例
 * NIO new I/O
 * @Author: rj
 * @Date: 2020-11-22 22:08
 * @Version: 1.0
 */
public class NioTest {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println("------初始数据------");
        System.out.println("position位置："+buffer.position()); // 0
        System.out.println("limit位置："+buffer.limit()); // 10
        System.out.println("capacity位置："+buffer.capacity()); // 10

        System.out.println("------填充数据 abcde ------");
        buffer.put("abcde".getBytes());
        System.out.println("position位置："+buffer.position()); // 5
        System.out.println("limit位置："+buffer.limit()); // 10
        System.out.println("capacity位置："+buffer.capacity()); // 10

        System.out.println("------flip 写模式切换到读模式 ------");
        buffer.flip();
        System.out.println("position位置："+buffer.position()); // 0
        System.out.println("limit位置："+buffer.limit()); // 5
        System.out.println("capacity位置："+buffer.capacity()); // 10

        System.out.println("开始读数据=====");
        byte[] dst = new byte[buffer.limit()];
        buffer.get(dst);
        System.out.println(new String(dst, 0, dst.length)); // abcde

        System.out.println("数据读取完毕======");
        System.out.println("position：" + buffer.position()); // 5
        System.out.println("limit：" + buffer.limit()); // 5
        System.out.println("capacity：" + buffer.capacity()); // 10

        // rewind()：表示重复读
        System.out.println("****** rewind 重复读 *******");
        buffer.rewind();
        System.out.println("position：" + buffer.position()); // 0
        System.out.println("limit：" + buffer.limit()); // 5
        System.out.println("capacity：" + buffer.capacity()); // 10

        System.out.println("clear() 清空缓冲区，读模式改成写模式，从0位置开始，但是缓冲区中的数据仍然存储，但是处于被遗忘状态");
        buffer.clear();
        System.out.println("position：" + buffer.position()); // 0
        System.out.println("limit：" + buffer.limit()); // 10
        System.out.println("capacity：" + buffer.capacity()); // 10
        buffer.put("agdfg".getBytes());
        System.out.println("写入数据 agdfg");
        buffer.flip();
        dst = new byte[2];
        buffer.get(dst);
        System.out.println("读取出来的数据："+new String(dst, 0, dst.length)); // ag
        System.out.println("position：" + buffer.position()); // 2
        System.out.println("limit：" + buffer.limit()); // 5
        System.out.println("capacity：" + buffer.capacity()); // 10

        System.out.println("compact() 读模式改成写模式，未读数据会放到数组头部位置，后续数据追加写入");
        buffer.compact();
        System.out.println("position：" + buffer.position()); // 3
        System.out.println("limit：" + buffer.limit()); // 10
        System.out.println("capacity：" + buffer.capacity()); // 10
        buffer.flip();
        dst = new byte[buffer.limit()];
        buffer.get(dst);
        System.out.println("读取出来的数据："+new String(dst, 0, dst.length)); // dfg
        System.out.println("position：" + buffer.position()); // 3
        System.out.println("limit：" + buffer.limit()); // 3
        System.out.println("capacity：" + buffer.capacity()); // 10
    }
}
