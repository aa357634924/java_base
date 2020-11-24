package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * 服务端
 * @Author: rj
 * @Date: 2020-11-23 16:31
 * @Version: 1.0
 */
public class ServerSocketChannelTest {
    public static void main(String[] args) throws IOException {
        // 创建服务端通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        // 设置非阻塞模式
        ssChannel.configureBlocking(false);
        // 绑定连接
        ssChannel.bind(new InetSocketAddress(8888));
        // 创建 selector
        Selector selector = Selector.open();
        // 将通道注册到选择器上,并设置监听事件为 接收就绪,也就是说客户端什么时候发送了，我才会开始获取连接
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 轮询式的获取选择器上已经准备就绪的事件
        while(selector.select() > 0){
            // 获取当前选择器中 所有注册的选择键（已就绪的监听事件）
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while(iterator.hasNext()){
                SelectionKey next = iterator.next();
                // 如果接收就绪，获取客户端连接
                if(next.isAcceptable()){
                    SocketChannel socketChannel = ssChannel.accept();
                    // 设置非阻塞模式
                    socketChannel.configureBlocking(false);
                    // 将该通道注册到选择器上，并监听读就绪状态
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }else if(next.isReadable()){
                    // 获取当前选择器上 读就绪 状态的通道
                    SocketChannel  socketChannel = (SocketChannel) next.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int len = 0;
                    // 通道写入数据到缓冲区
                    while((len = socketChannel.read(buffer)) > 0) {
                        // 切换成读取模式
                        buffer.flip();
                        // 打印客户端的发送
                        System.out.println(Thread.currentThread().getName() + "\t  " + new String(buffer.array(), 0, len));
                        // 清空缓存
                        buffer.clear();
                    }
                }
                // 操作执行完成后，需要将 选择键给取消 SelectionKey
                iterator.remove();
            }
        }
    }
}
