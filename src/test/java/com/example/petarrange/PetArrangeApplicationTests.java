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

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
class PetArrangeApplicationTests {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private UserService userService;

    @Test
    void contextLoads() throws Exception {

            // 模拟 GET 请求并验证响应
            mockMvc.perform(get("/user/adduser").param("asd","123"))
                    .andExpect(status().isOk()); // HTTP 状态码 200



    }


}
