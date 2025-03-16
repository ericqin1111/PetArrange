package com.example.petarrange;

import com.example.petarrange.entity.Category;
import com.example.petarrange.persistence.CategoryMapper;
import com.example.petarrange.utils.AESUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PetArrangeApplicationTests {

    @Autowired
    private CategoryMapper categoryMapper;


    @Test
    void contextLoads() {


        String content = "hello world";

        // 加密
        String encrypted = AESUtils.encrypt(content);
        System.out.println("加密后: " + encrypted);

        // 解密
        String decrypted = AESUtils.decrypt(encrypted);
        System.out.println("解密后: " + decrypted);


    }

}
