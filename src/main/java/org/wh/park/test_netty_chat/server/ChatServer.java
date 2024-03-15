package org.wh.park.test_netty_chat.server;

import io.netty.channel.nio.NioEventLoopGroup;

/**
 * @author ChenWeihan
 * @description TODO
 */
public class ChatServer {
    public static void main(String[] args) {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
    }
}
