package org.wh.park.test_netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author ChenWeihan
 * @description TODO
 */
public class HelloServer {

    public static void main(String[] args) {

        // 1. 服务器端的启动器 「负责组装netty组件 启动服务器」
        new ServerBootstrap()
                // 2. 添加 NioEventLoopGroup 组件：ventLoop「selector，thread」
                .group(new NioEventLoopGroup())
                // 3. 选择一个服务器 ServerChannel的实现
                .channel(NioServerSocketChannel.class) // OIO BIO
                // 4.childHandler boss负责处理连接   work(child) 负责处理读写，决定了能干哪些操作(Handler)
                .childHandler(
                        // 5. 代表和客户端进行数据读写的通道  Initializer 初始化     负责添加别的handler
                        new ChannelInitializer<NioSocketChannel>() {
                            @Override
                            protected void initChannel(NioSocketChannel ch) throws Exception {
                                // 6. 添加具体的 Handler
                                ch.pipeline().addLast(new StringDecoder()); // 将 ByteBuf 转为字符串
                                ch.pipeline().addLast(new ChannelInboundHandlerAdapter() { // 自定义的 Handler
                                    // 读事件
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        // 打印上一步转换好的内容
                                        System.out.println(msg);
                                    }
                                });
                            }
                        })
                // 7. 绑定监听端口
                .bind(8080);
    }
}
