package com.example.petarrange.controller;

import com.example.petarrange.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String selectAllUser(Model model) {

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
    public int addUser(@Param("username") String username, @Param("password") String password) {
        User user = new User();
        user = userService.findUserByName(username);
        if (user == null) {
            return userService.addUser(username, password);
        } else {
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
    public String toUpdateUser(Model model, String username) {

        User user = userService.findUserByName(username);
        model.addAttribute("user", user);
        //跳转到用户修改页面，同时将要修改的用户的信息传递过去
        return "updateUser";
    }

    //正式更新用户
    @RequestMapping("/updateUser")
    public String updateUser(@ModelAttribute User user) {

        System.out.println(user.toString());
        userService.updateUser(user);
        System.out.println(user.getUsername());
        return "redirect:/user/allUser";
    }

    //更新
    @PostMapping("/addUser")
    @ResponseBody
    public ResponseEntity<Integer> addUser(@RequestBody User user) {
        userService.save(user);

        return ResponseEntity.ok(1);
    }

    //更新
    @PutMapping("/updateUser/{id}")
    @ResponseBody
    public ResponseEntity<Integer> update(@PathVariable Integer id, @RequestBody User user) {
        user.setId(id);
        userService.updateById(user);
        return ResponseEntity.ok(1);
    }

    //删除
    @PostMapping("/delUser/{id}")
    @ResponseBody
    public ResponseEntity<Integer> delUser(@PathVariable Integer id) {
        userService.removeById(id);

        return ResponseEntity.ok(1);
    }
//    public ResponseEntity<String> delUser(@RequestBody Map<String, List<String>> requestBody) {
//        List<String> userList = requestBody.get("userList");
//        userService.delUser(userList);
//        if (userList == null || userList.isEmpty()) {
//            return ResponseEntity.badRequest().body("用户列表为空");
//        }
//
//        return ResponseEntity.ok("删除成功");
//    }

    //查询用户 根据用户名查询
    @RequestMapping("/queryUser")
    public String queryUser(String userName, Model model) {

        User user = userService.findUserByName(userName);
        model.addAttribute(user);
        return "allUser";
    }

    @RequestMapping("/getUser/{id}")
    @ResponseBody
    public User getUser(@PathVariable Integer id) {
        return userService.getById(id);
    }

    //    @GetMapping("/FormPage")
//    public String Form(HttpSession session,Model model) {
//
//        Object pageStr = session.getAttribute("page");
//        int page = (pageStr != null) ? (int) pageStr : 1;
//
//        int pageSize = 10;
//        System.out.println("page" + page);
//        int offset = (page - 1) * pageSize;
//        int totalRecord = 0;
//
//
//
//        List<User> userList  = userService.selectPageUser(pageSize, offset);
//        System.out.println("userList" + userList);
//
//        totalRecord = userService.countNum();
//// 将总记录数存入 session
//        session.setAttribute("totalRecord", totalRecord);
//// 总页数应通过总记录数 / 每页条数计算
//        int totalPages = (int) Math.ceil(totalRecord / (double) pageSize);
//
//        session.setAttribute("page", page);
//        session.setAttribute("userList", userList);
//        session.setAttribute("totalPages", totalPages);
//        session.setAttribute("totalRecord", totalRecord);
//        session.setAttribute("pageSize", pageSize);
//
//        // 构造返回数据，保证JSON结构为 { code: 0, count: totalRecord, data: userList }
//        Map<String, Object> resultMap = new HashMap<>();
//        resultMap.put("code", 0);
//        resultMap.put("count", totalRecord);
//        resultMap.put("data", userList);
//        session.setAttribute("resultMap", resultMap);
//        model.addAttribute("page", page);
//        model.addAttribute("totalPages", totalPages);
//
//        model.addAttribute("userList", userList);
//        return "/userForm";
//    }
    @GetMapping("/FormPage")
    public String formPage(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {

        int pageSize = 10;
        int offset = (page - 1) * pageSize;

        // 查询分页数据
        List<User> userList = userService.selectPageUser(pageSize, offset);
        // 查询总记录数
        int totalRecord = userService.countNum();
        // 计算总页数
        int totalPages = (int) Math.ceil(totalRecord / (double) pageSize);

        // 将数据放入 Model
        model.addAttribute("page", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("userList", userList);
        model.addAttribute("totalRecord", totalRecord);
        model.addAttribute("pageSize", pageSize);
        return "/userForm";
    }
        @ResponseBody
        @GetMapping("/updateUser")
        public Map<String, Object> updateUser ( @Param("page") int page, HttpSession session){

            int pageSize = 10;
            System.out.println("page" + page);
            int offset = (page - 1) * pageSize;
            int totalRecord = 0;

            List<User> userList = new ArrayList<>();

            userList = userService.selectPageUser(pageSize, offset);

            totalRecord = userService.countNum();
// 将总记录数存入 session
            session.setAttribute("totalRecord", totalRecord);
// 总页数应通过总记录数 / 每页条数计算
            int totalPages = (int) Math.ceil(totalRecord / (double) pageSize);

            session.setAttribute("page", page);
            session.setAttribute("userList", userList);
            session.setAttribute("totalPages", totalPages);
            session.setAttribute("totalRecord", totalRecord);
            session.setAttribute("pageSize", pageSize);

            // 构造返回数据，保证JSON结构为 { code: 0, count: totalRecord, data: userList }
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("code", 0);
            resultMap.put("count", totalRecord);
            resultMap.put("data", userList);
            return resultMap;
        }

        @GetMapping("/search")
        public String searchUserLike (@Param("value") String value, HttpSession session, Model model){


            Map<String, Object> map = new HashMap<>();

            Object pageStr = session.getAttribute("page");
            int page = 1;
            int pageSize = 5;
            int offset = 0;
            int totalRecord = 0;
            int totalPages = 0;

            List<User> userList = userService.findUsersByUsernameLike(value);
            totalRecord = userList.size();
            totalPages = (int) Math.ceil(totalRecord / (double) pageSize);


            // 构造返回数据，保证JSON结构为 { code: 0, count: totalRecord, data: userList }
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("code", 0);
            resultMap.put("count", totalRecord);
            resultMap.put("data", userList);
            session.setAttribute("resultMap", resultMap);
            model.addAttribute("page", page);
            model.addAttribute("totalPages", totalPages);

            model.addAttribute("userList", userList);
            return "/userForm";
        }
    }
