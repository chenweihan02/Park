package org.wh.park.test_mp.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ChenWeihan
 * @description MyBatisPlus配置类
 */

@Configuration
public class MyBatisPlusConfiguration {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(dynamicTableNameInnerInterceptor());
        return interceptor;
    }


    /**
     * 注册动态表名拦截器
     *
     * @return
     */
    private DynamicTableNameInnerInterceptor dynamicTableNameInnerInterceptor() {
        DynamicTableNameInnerInterceptor dynamicTableNameInnerInterceptor =
                new DynamicTableNameInnerInterceptor();

        dynamicTableNameInnerInterceptor.setTableNameHandler(
                new IdTableNameHandler()
        );

        return dynamicTableNameInnerInterceptor;
    }
}
