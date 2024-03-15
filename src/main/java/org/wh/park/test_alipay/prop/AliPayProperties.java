package org.wh.park.test_alipay.prop;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author ChenWeihan
 * @description TODO
 */


@Data
@Configuration
@ConfigurationProperties(prefix = "alipay.easy")
public class AliPayProperties {

    private String protocol;

    private String getewayHost;

    private String signType;

    private String appId;

    private String merchantPrivateKey;

    private String alipayPublicKey;

    private String notifyUrl;

    private String encryptKey;
}
