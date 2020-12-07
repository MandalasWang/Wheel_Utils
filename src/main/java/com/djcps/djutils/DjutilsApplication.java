package com.djcps.djutils;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author wyy
 * @version 1.0
 * @Classname TestController
 * @date 2020/12/3 9:33
 * @description 启动类
 **/
@SpringBootApplication
@MapperScan(basePackages = "com.djcps.djutils.**")
public class DjutilsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DjutilsApplication.class, args);
    }

}
