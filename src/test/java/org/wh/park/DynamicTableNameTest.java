package org.wh.park;

import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wh.park.test_mp.config.IdTableNameHandler;
import org.wh.park.test_mp.domain.User;
import org.wh.park.test_mp.mapper.UserMapper;

import java.util.List;

/**
 * @author ChenWeihan
 * @description TODO
 */
@Slf4j
@SpringBootTest
public class DynamicTableNameTest {

    @Resource
    private UserMapper userMapper;

    @Test
    void testInsert() {
        IdTableNameHandler.initCurrentId(1L);
        User user = new User();
        user.setId(1L);
        user.setUsername("张三1");
        user.setPassword("张三1");
        userMapper.insert(user);

        IdTableNameHandler.initCurrentId(12L);
        User user1 = new User();
        user1.setId(2L);
        user1.setUsername("李四1");
        user1.setPassword("李四1");
        userMapper.insert(user1);
    }


    @Test
    void testSelectList() {
        IdTableNameHandler.initCurrentId(12L);
        List<User> users = userMapper.selectList(null);
        log.debug("users = {}", JSON.toJSONString(users));
    }

}
