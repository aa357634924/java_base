package nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 复制本地文件
 * @Author: rj
 * @Date: 2020-11-23 15:36
 * @Version: 1.0
 */
public class FileChannelTest {
    public static void main(String[] args) throws IOException {
        FileInputStream in = new FileInputStream("D:\\photo\\1.png");
        FileOutputStream out = new FileOutputStream("D:\\photo\\2.png");
        // 写入缓冲区
        FileChannel inChannel = in.getChannel();
        // 从缓冲区读取数据
        FileChannel outChannel = out.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while(inChannel.read(buffer) != -1){ // 缓冲区写入到通道
            buffer.flip(); // 写模式切换到读模式
            outChannel.write(buffer);
            buffer.clear(); // 读模式切换到写模式
        }
        out.close();
        in.close();
        System.out.println("复制成功。。。");
    }
}
