package com.zzs.guli.ucenter;

import javafx.application.Application;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: guli_parent-master
 * @description:
 * @author: dfg-md
 * @create: 2022-12-15 14:37
 **/
@SpringBootApplication
@ComponentScan(basePackages = {"com.zzs"})
@MapperScan("com.zzs.guli.ucenter.mapper")
public class ServiceUcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUcenterApplication.class,args);
    }
}
