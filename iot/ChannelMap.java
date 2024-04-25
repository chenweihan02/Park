//package com.ant.robot.iot;
//
//import io.netty.channel.Channel;
//import io.netty.channel.ChannelId;
//import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
//import org.springframework.util.CollectionUtils;
//
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * @author ChenWeihan
// * @description 管理通道Map类
// */
//public class ChannelMap {
//
//    /**
//     * 管理一个全局map，保存连接进服务端的通道数量
//     */
//    private static final ConcurrentHashMap<ChannelId, Channel> CHANNEL_MAP = new ConcurrentHashMap<>();
//
//    public static ConcurrentHashMap<ChannelId, Channel> getChannelMap() {
//        return CHANNEL_MAP;
//    }
//
//    /**
//     * 获取指定name的channel
//     *
//     * @param channelId
//     * @return
//     */
//    public static Channel getChannelByName(ChannelId channelId) {
//        if (CollectionUtils.isEmpty(CHANNEL_MAP)) {
//            return null;
//        }
//        return CHANNEL_MAP.get(channelId);
//    }
//
//    /**
//     * 将通道中的消息推送到每一个客户端
//     *
//     * @param obj
//     * @return
//     */
//    public static boolean pushNewsToAllClient(String obj) {
//        if (CollectionUtils.isEmpty(CHANNEL_MAP)) {
//            return false;
//        }
//        for (ChannelId channelId : CHANNEL_MAP.keySet()) {
//            Channel channel = CHANNEL_MAP.get(channelId);
//            channel.writeAndFlush(new TextWebSocketFrame(obj));
//        }
//        return true;
//    }
//
//    /**
//     * 将channel和对应的name添加到ConcurrentHashMap
//     *
//     * @param channelId
//     * @param channel
//     */
//    public static void addChannel(ChannelId channelId, Channel channel) {
//        CHANNEL_MAP.put(channelId, channel);
//    }
//
//    /**
//     * 移除掉name对应的channel
//     *
//     * @param channelId
//     * @return
//     */
//    public static boolean removeChannelByName(ChannelId channelId) {
//        if (CHANNEL_MAP.containsKey(channelId)) {
//            CHANNEL_MAP.remove(channelId);
//            return true;
//        }
//        return false;
//    }
//
//}