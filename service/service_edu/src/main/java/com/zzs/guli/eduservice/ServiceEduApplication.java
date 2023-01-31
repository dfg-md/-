package com.zzs.guli.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description: 课程管理
 * @Author: StarSea99
 * @Date: 2020-09-27 15:57
 */
@SpringBootApplication
//注册Nacos客户端
@EnableDiscoveryClient
////服务调用
@EnableFeignClients
@ComponentScan(basePackages = {"com.zzs"})
@EnableSwagger2
public class ServiceEduApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceEduApplication.class, args);
    }

}
