package com.example.petarrange;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.petarrange.persistence")
//该包下所有的类都用mybatics生成Mapper（类）
public class PetArrangeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetArrangeApplication.class, args);
    }

}
