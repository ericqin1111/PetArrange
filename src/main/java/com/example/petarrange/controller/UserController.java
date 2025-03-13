package com.example.petarrange.controller;

import com.example.petarrange.entity.User;
import com.example.petarrange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {


    // controller层调用service层，service层调用dao层 mvc架构，我就不多说了
    //@Qualifier注解 别名

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;



    @RequestMapping("/allUser")
    public String  selectAllUser(Model model){

        //使用业务层调用dao层查询出数据，通过model对象渲染到前台页面
        List<User> userList = userService.selectAllUser();
        model.addAttribute(userList);
        return "allUser";
    }


    //添加书籍，首先需要跳转到添加用户的表单页面
    @RequestMapping("/toAddUser")
    public String toAddUser(){

        //接收到前端请求后，跳到添加用户表单页面
        return "addUser";
    }

    //接收添加用户表单的数据，进行正式的添加用户，添加完成后，重定向到所有用户页面
    @RequestMapping("addUser")
    public String addUser(User user){

        userService.addUser(user);
        System.out.println(user.toString());
        return "redirect:/user/allUser";
    }

    //更新用户
    @RequestMapping("toUpdateUser")
    public String toUpdateUser(Model model,String username){

        User user = userService.findUserByName(username);
        model.addAttribute("user",user);
        //跳转到用户修改页面，同时将要修改的用户的信息传递过去
        return "updateUser";
    }

    //正式更新用户
    @RequestMapping("updateUser")
    public String updateUser(User user){

        System.out.println(user.toString());
        userService.updateUser(user);
        System.out.println(user.getUserName());
        return "redirect:/user/allUser";
    }


    //删除
    @RequestMapping("delUser")
    public String delUser(User user){

        userService.delUser(user);
        return "redirect:/user/allUser";
    }


    //查询用户 根据用户名查询
    @RequestMapping("/queryUser")
    public  String queryUser(String userName,Model model){

        User user= userService.findUserByName(userName);
        model.addAttribute(user);
        return "allUser";
    }
}
