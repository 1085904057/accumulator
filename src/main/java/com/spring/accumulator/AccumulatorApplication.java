package com.spring.accumulator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.spring.accumulator.dao")
@SpringBootApplication
public class AccumulatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccumulatorApplication.class, args);
    }

}
