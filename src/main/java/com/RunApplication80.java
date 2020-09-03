package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yangzx
 */
@SpringBootApplication
@MapperScan(value = "com.excel.mapper")
public class RunApplication80 {
    public static void main(String[] args) {
        SpringApplication.run(RunApplication80.class,args);
    }
}
