package org.wh.park.iot.server;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author ChenWeihan
 * @description 配置类
 */

@Data
@ToString
@Component
@Configuration
@ConfigurationProperties(prefix = "socket")
public class SocketProperties {
    private Integer port;
    private String host;
}
