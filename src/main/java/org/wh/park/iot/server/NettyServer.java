package org.wh.park.iot.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * @author ChenWeihan
 * @description Netty服务启动类
 */
@Slf4j
@Component
public class NettyServer {
    public void start(InetSocketAddress address) {
        //配置服务器的NIO线程组
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // 绑定线程池， 编码解码
            // 服务器端接收连接队列长度，如果队列已满， 客户端连接将被拒绝

            ServerBootstrap bootstrap = new ServerBootstrap()
                    .group(bossGroup, workerGroup)
                    // 指定 Channel
                    .channel(NioServerSocketChannel.class)
                    // 使用指定的接口设置套接字地址
                    .localAddress(address)
                    // 使用自定义处理类
                    .childHandler(new NettyServerChannelInitializer())
                    // 服务端可连接队列数，对应 TCP/IP协议listen函数中的backlog参数
                    .option(ChannelOption.SO_BACKLOG, 128)
                    //保持长连接 2小时无数据激活心跳机制
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    // 将小的数据包包装成更大的帧进行传输，提高网络的负载
                    .childOption(ChannelOption.TCP_NODELAY, true);

            //绑定端口，开始节后进来的连接
            ChannelFuture future = bootstrap.bind(address).sync();
            if (future.isSuccess()) {
                log.info("netty服务器开始监听端口: {}", address.getPort());
            }
            //关闭channel和块 直到它被关闭
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        } finally {
            //bossGroup.shutdownGracefully();
            //workerGroup.shutdownGracefully();
        }
    }


}
