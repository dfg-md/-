package com.zzs.guli.sta;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @program: guli_parent-master
 * @description:
 * @author: dfg-md
 * @create: 2023-01-01 14:08
 **/
@SpringBootApplication
@ComponentScan(basePackages = {"com.zzs"})
@EnableDiscoveryClient
@MapperScan("com.zzs.guli.sta.mapper")
@EnableFeignClients
@EnableScheduling
public class ServiceStaApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceStaApplication.class, args);
    }
}
