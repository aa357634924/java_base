package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * 服务端
 * @Author: rj
 * @Date: 2020-11-23 16:31
 * @Version: 1.0
 */
public class SocketChannelTest {
    public static void main(String[] args) throws IOException {
// 建立连接
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost",8888));
        // 设置非阻塞模式
        channel.configureBlocking(false);
        // 创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 向缓冲区写入数据
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String str = scanner.nextLine();
            if("bye".equals(str)){
                channel.close();
//                return;
            }
            // 向缓冲区写入数据
            buffer.put(str.getBytes());
            // 缓冲区从写模式切换到读模式
            buffer.flip();
            // 将缓冲区的数据写入通道，通道读取缓冲区数据
            channel.write(buffer);
            // 清空缓冲区
            buffer.clear();
        }
    }
}
