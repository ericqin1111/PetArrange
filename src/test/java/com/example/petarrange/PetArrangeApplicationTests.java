package com.example.petarrange;

import com.example.petarrange.entity.Category;
import com.example.petarrange.persistence.CategoryMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PetArrangeApplicationTests {

    @Autowired
    private CategoryMapper categoryMapper;


    @Test
    void contextLoads() {
        Category category=categoryMapper.selectById("CATS");
        System.out.println(category);
    }

}
