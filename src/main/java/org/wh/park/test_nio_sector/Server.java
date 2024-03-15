package org.wh.park.test_nio_sector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenWeihan
 * @description TODO
 */

public class Server {

    public static void main(String[] args) throws IOException {
        // 使用 nio 来阻塞模式 单线程处理

        //0. ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(16);

        //1. 创建了服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        //2. 绑定监听端口
        ssc.bind(new InetSocketAddress(8080));

        //3. 连接集合
        List<SocketChannel> channels = new ArrayList<>();
        while (true) {
            //4. accept 建立与客户端连接 socketChannel 用来与客户端之间通信
            System.out.println("connecting");
            SocketChannel sc = ssc.accept(); // 阻塞方法

            if (sc != null) {
                System.out.println("connected");
                sc.configureBlocking(false);
                channels.add(sc);
            }

            for (SocketChannel channel : channels) {
                System.out.println("channel = " + channel);
                //5. 接收客户端发送的数据
                int read = channel.read(buffer);
                if (read > 0) {
                    buffer.flip();
                    System.out.println(buffer);
                    buffer.clear();
                    System.out.println("channel finish");
                }

            }
        }
    }
}
