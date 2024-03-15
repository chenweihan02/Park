package org.wh.park.test_nio_sector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author ChenWeihan
 * @description TODO poll 和 epoll
 */

public class SelectorServer {

    /**
     * accept - 会在有连接请求时触发
     * connect - 是客户端，连接建立后触发
     * read - 可读事件
     * write - 可写事件
     */


    public static void main(String[] args) throws IOException {

//         1. 创建selector
        Selector selector = Selector.open();

        ByteBuffer buffer = ByteBuffer.allocate(16);
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);


//        2. 建立selector和channel之间的联系
//        selectionKey 就是将来事件发生后，通过它可以知道事件和哪个channel的事件
        SelectionKey sscKey = ssc.register(selector, 0, null); //0-不关注任何事件
//        key只关注 accept 时间
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        System.out.println("sscKey = " + sscKey);


        ssc.bind(new InetSocketAddress(8080));
        while (true) {
            //3.select 方法, 没有事件发生，线程阻塞，有事件，恢复运行
            // 事件发生后要么取消，要么处理
            // TODO epoll 水平
            // select 在事件未处理时，它不会阻塞, 事件处理了或取消了就不会阻塞
            selector.select();

            //4.处理事件 selectedKeys 内部包含了所有发生的事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                System.out.println("key = " + key);

                //5. 区分事件类型
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = channel.accept();
                    System.out.println("sc = " + sc);
                    SelectionKey scKey = sc.register(selector, 0, null);
                    scKey.interestOps(SelectionKey.OP_READ);

                } else if (key.isReadable()) {

                }


                //key cancel()
            }

        }
    }
}
