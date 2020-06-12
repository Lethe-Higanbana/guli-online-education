package com.summersky.guli.service.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Lenovo
 * @Authur:zengfanbin
 * @Date:2020-6-12
 * @Time:22:28
 * @Description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) // 取消数据源自动配置
@ComponentScan({"com.summersky.guli"})
public class ServiceOssApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOssApplication.class,args);
    }
}
