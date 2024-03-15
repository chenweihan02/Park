package org.wh.park;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ChenWeihan
 * @description TODO
 */
@SpringBootApplication
@MapperScan("org.wh.park.test_mp.mapper")
public class ParkApplication {
    public static void main(String[] args) {
        SpringApplication.run(ParkApplication.class, args);
    }
}
