package com.example.petarrange.controller;

import com.example.petarrange.entity.User;
import com.example.petarrange.service.UserService;
import com.example.petarrange.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {


    // controller层调用service层，service层调用dao层 mvc架构，我就不多说了
    //@Qualifier注解 别名

    @Autowired
    private UserServiceImpl userService;



    @GetMapping("/allUser")
    @ResponseBody
    public String  selectAllUser(Model model){

        //使用业务层调用dao层查询出数据，通过model对象渲染到前台页面
        List<User> userList = userService.selectAllUser();
        model.addAttribute(userList);
        return "allUser";
    }


    //添加书籍，首先需要跳转到添加用户的表单页面
//    @RequestMapping("/toAddUser")
//    public String toAddUser(@Param("username")String username,@Param("password") String password){
//
//
//        //接收到前端请求后，跳到添加用户表单页面
//        return "addUser";
//    }

    //接收添加用户表单的数据，进行正式的添加用户，添加完成后，重定向到所有用户页面
    @RequestMapping("/addUser")
    @ResponseBody
    public int addUser(@Param("username")String username,@Param("password")String password){
        User user=new User();
        user=userService.findUserByName(username);
        if(user==null){
            return userService.addUser(username,password);
        }
        else{
            return userService.updateUser(user);
        }
    }

//    @PostMapping("/add")
//    public void add(@Param("username")String username,@Param("password")String password){
//        userService.addUser(username,password);
//        return;
//    }

    //更新用户
    @RequestMapping("/toUpdateUser")
    public String toUpdateUser(Model model,String username){

        User user = userService.findUserByName(username);
        model.addAttribute("user",user);
        //跳转到用户修改页面，同时将要修改的用户的信息传递过去
        return "updateUser";
    }

    //正式更新用户
    @RequestMapping("/updateUser")
    public String updateUser(@ModelAttribute User user){

        System.out.println(user.toString());
        userService.updateUser(user);
        System.out.println(user.getUsername());
        return "redirect:/user/allUser";
    }


    //删除
    @PostMapping("/delUser")
    @ResponseBody
//    public int delUser(@RequestBody ArrayList<String> userList){
//        return userService.delUser(userList);
//    }

    public ResponseEntity<String> delUser(@RequestBody Map<String, List<String>> requestBody) {
        List<String> userList = requestBody.get("userList");
        userService.delUser(userList);
        if (userList == null || userList.isEmpty()) {
            return ResponseEntity.badRequest().body("用户列表为空");
        }

        return ResponseEntity.ok("删除成功");
    }

    //查询用户 根据用户名查询
    @RequestMapping("/queryUser")
    public  String queryUser(String userName,Model model){

        User user= userService.findUserByName(userName);
        model.addAttribute(user);
        return "allUser";
    }

    @GetMapping("/FormPage")
    public String Form(HttpSession session){

        Object pageStr=session.getAttribute("page");
        int page = (pageStr != null) ? (int)pageStr : 1;
        int pageSize=5;
        int offset=(page-1)*pageSize;
        int totalRecord=0;
        int totalPages=0;

        List<User> userList = new ArrayList<>();

        session.setAttribute("page",page);

        userList=userService.selectPageUser(pageSize,offset);

        totalRecord=userService.count();
        totalPages=(int)Math.ceil(totalRecord/(double)pageSize);

        session.setAttribute("page",page);
        session.setAttribute("userList",userList);
        session.setAttribute("totalPages",totalPages);

        return "/userForm";
    }

    @ResponseBody
    @GetMapping("/updateUser")
    public Map<String, Object> updateUser(@Param("page") int page, HttpSession session){

        Map<String,Object> map=new HashMap<>();

        int pageSize=5;
        System.out.println("page"+page);
        int offset=(page-1)*pageSize;
        int totalRecord=0;
        int totalPages=0;
        List<User> userList;

        userList=userService.selectPageUser(pageSize,offset);
        totalRecord=userService.count();
        totalPages=(int)Math.ceil(totalRecord/(double)pageSize);

        session.setAttribute("page",page);
        session.setAttribute("userList",userList);
        session.setAttribute("totalPages",totalPages);


        map.put("page",page);
        map.put("userList",userList);
        map.put("totalPages",totalPages);

        return map;
    }

    @GetMapping("/search")
    @ResponseBody
    public List<User> searchUserLike(@Param("value") String value,HttpSession session){

        List<User> userList=userService.findUsersByUsernameLike(value);
//        int page=1;
//        int pageSize=5;
////        System.out.println("page"+page);
//        int offset=(page-1)*pageSize;
//        int totalRecord=0;
//        int totalPages=0;
        return userList;
    }
}
