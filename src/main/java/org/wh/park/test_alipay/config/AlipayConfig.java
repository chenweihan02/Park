package org.wh.park.test_alipay.config;

import com.alipay.easysdk.kernel.Config;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wh.park.test_alipay.prop.AliPayProperties;

/**
 * @author ChenWeihan
 * @description TODO
 */
@Configuration
@Data
public class AlipayConfig {

    @Bean
    public Config config(AliPayProperties payProperties) {
        Config config = new Config();
        config.protocol = payProperties.getProtocol();
        config.gatewayHost = payProperties.getGetewayHost();
        config.signType = payProperties.getSignType();
        config.appId = payProperties.getAppId();
        config.merchantPrivateKey = payProperties.getMerchantPrivateKey();
        config.alipayPublicKey = payProperties.getAlipayPublicKey();

        //可设置异步同志接收服务器地址（可选）
        config.notifyUrl = payProperties.getNotifyUrl();
        config.encryptKey = "";
        return config;
    }
}
