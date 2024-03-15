package org.wh.park.iot.server;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author ChenWeihan
 * @description 服务端初始化，客户端与服务端一旦连接，该类中方法会被毁掉，设置出站编码器和入站编码器
 */
public class NettyServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //接收消息格式，使用自定义解析数据格式
        pipeline.addLast("decoder", new MyDecoder());
        //发送消息格式，使用自定义解析数据格式
        pipeline.addLast("encoder", new MyEncoder());

        //针对客户端，如果在1分钟时没有向服务器发送写心跳 ，则主动断开
        //如果是读空闲或者写空闲  不处理 这里根据业务修改
        //pipeline.addLast(new IdleStateHandler(600, 0, 0, TimeUnit.SECONDS));
        //自定义的空闲检测
        pipeline.addLast(new NettyServerHandler());
    }
}
