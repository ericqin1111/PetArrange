package com.example.petarrange;

import com.example.petarrange.controller.UserController;
import com.example.petarrange.entity.Category;
import com.example.petarrange.persistence.CategoryMapper;
import com.example.petarrange.service.UserService;
import com.example.petarrange.service.impl.UserServiceImpl;
import com.example.petarrange.utils.AESUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
class PetArrangeApplicationTests {

    @Autowired
    private CategoryMapper categoryMapper;





    @Test
    void contextLoads() throws Exception {

        String a="abc";
        String b=AESUtils.encrypt(a);
        String c=AESUtils.decrypt(b);
        System.out.println("encry:"+b);
        System.out.println("decry"+c);
        return;



    }


}
