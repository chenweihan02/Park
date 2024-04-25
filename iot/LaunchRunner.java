//package com.ant.robot.iot;
//
//import cn.hutool.cron.CronUtil;
//import jakarta.annotation.Resource;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.net.InetSocketAddress;
//
///**
// * @author ChenWeihan
// * @description 任务队列
// */
//@Component
//@Slf4j
//public class LaunchRunner implements CommandLineRunner {
//
//    @Resource
//    private NettyServer nettyServer;
//
//    @Resource
//    private SocketProperties socketProperties;
//
//    @Override
//    public void run(String... args) throws Exception {
//        TaskRunner();
//        InetSocketAddress address = new InetSocketAddress(socketProperties.getHost(), socketProperties.getPort());
//        log.info("netty服务器启动地址: {}", socketProperties.getHost());
//        nettyServer.start(address);
//    }
//
//    /**
//     * 执行正在运行的任务
//     */
//    private void TaskRunner() {
//        /**
//         * 任务队列启动
//         */
//        CronUtil.setMatchSecond(true);
//        CronUtil.start();
//        log.info("\n-----------------------任务服务启动------------------------\n\t" +
//                        "当前正在启动的{}个任务"+
//                        "\n-----------------------------------------------------------\n\t"
//                , CronUtil.getScheduler().size()
//
//        );
//    }
//}
