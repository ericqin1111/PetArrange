package com.example.petarrange.controller;

import com.example.petarrange.entity.Admin;
import com.example.petarrange.service.impl.AccountServiceImpl;
import com.example.petarrange.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
public class AccountController {


    @Autowired
    AccountServiceImpl accountService;

    @Autowired
    UserServiceImpl userService;
    @GetMapping("loginForm") //前端页面通过/account/loginForm访问,进入登入页面
    public String loginForm(Model model){
       Admin admin=new Admin();
        model.addAttribute("admin",admin);// 给前端一个admin对象，如果前端的提交的数据和admin对象中的数据名相同，就会直接匹配
        return "/account/login";//进入登入页面
    }

    @PostMapping("login") //登录的验证
    public String login(@ModelAttribute Admin admin, HttpSession session){
        System.out.println("我在login里");
        if(accountService.isAdiminExist(admin.getAdminName(),admin.getPassword())){

            return "redirect:/catalog/index";//登录成功返回到主页

        }

        return "/account/login"; //失败重定向到错误页面或者当前页面
    }

    @GetMapping("registerForm") //进入注册页面
    public String registerForm(Model model){
        Admin admin=new Admin();
        model.addAttribute("admin",admin);//注册也是同理，form表单提交的变量名要和admin中的属性名相同
        return "/test123";//进入到注册页面
    }

    @PostMapping("register")  //注册的验证
    public String register(@ModelAttribute Admin admin, HttpSession session){
        if(!accountService.isAdminNameExist(admin.getAdminName())){
            accountService.insertAdmin(admin.getAdminName(), admin.getPassword());
            session.setAttribute("loginAccount", admin.getAdminName());
            System.out.println("注册成功");
            return "/catalog/main";//注册成功进入主页
        }
        return "/account/test";//失败导入错误页面或者……
    }


    @GetMapping("logout")
    public String logout(HttpSession session) {
        // 清空 session
        session.invalidate(); // 使当前会话失效，清除所有的 session 数据
        return "redirect:/catalog/index"; // 重定向到首页或任何你想刷新页面的路径
    }



}
