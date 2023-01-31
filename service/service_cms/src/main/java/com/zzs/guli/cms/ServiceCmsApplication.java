package com.zzs.guli.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: guli_parent-master
 * @description:
 * @author: dfg-md
 * @create: 2022-12-14 18:25
 **/
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.zzs"})
@MapperScan("com.zzs.guli.cms.mapper")
public class ServiceCmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCmsApplication.class,args);
    }
}
