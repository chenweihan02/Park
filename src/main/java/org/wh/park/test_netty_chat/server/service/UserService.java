package org.wh.park.test_netty_chat.server.service;

/**
 * @author ChenWeihan
 * @description TODO
 */
public interface UserService {


    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    boolean login(String username, String password);
}
