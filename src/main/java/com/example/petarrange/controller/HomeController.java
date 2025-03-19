package com.example.petarrange.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

//    @GetMapping("index")
//    public String index(){
//        return "home/home";
//    }

    @GetMapping("/main")
    public String mainTest(){
        return "main";
    }

    @GetMapping("/index")
    public String fish(Model model) {
        model.addAttribute("title", "首页");
        return "pages/index"; // 返回视图名称，Thymeleaf会查找templates/pages/index.html
    }

    @GetMapping("/userManagement")
    public String dogs(Model model) {
        model.addAttribute("title", "用户管理");
        return "pages/userManagement"; // 返回视图名称，Thymeleaf会查找templates/pages/userManagement.html
    }

    @GetMapping("/productManagement")
    public String cats(Model model) {
        model.addAttribute("title", "商品管理");
        return "pages/productManagement"; // 返回视图名称，Thymeleaf会查找templates/pages/productManagement.html
    }

    @GetMapping("/orderManagement")
    public String reptiles(Model model) {
        model.addAttribute("title", "订单管理");
        return "pages/orderManagement"; // 返回视图名称，Thymeleaf会查找templates/pages/orderManagement.html
    }
}
