package com.summersky.guli.service.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Lenovo
 * @Authur:zengfanbin
 * @Date:2020-6-11
 * @Time:9:46
 * @Description:
 */
@SpringBootApplication
@ComponentScan({"com.summersky.guli"}) // 启动的时候扫描包，包括其他模块的
@EnableFeignClients
@EnableDiscoveryClient
public class ServiceEduApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceEduApplication.class,args);
    }
}
