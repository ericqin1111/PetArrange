package com.example.petarrange;

import com.example.petarrange.persistence.CategoryMapper;
import com.example.petarrange.utils.AESUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
class PetArrangeApplicationTests {

    @Autowired
    private CategoryMapper categoryMapper;





    @Test
    void contextLoads() throws Exception {

        String a="abc";
        String b= AESUtils.encrypt(a);
        String c=AESUtils.decrypt(b);
        System.out.println("encry:"+b);
        System.out.println("decry"+c);
        return;



    }


}
