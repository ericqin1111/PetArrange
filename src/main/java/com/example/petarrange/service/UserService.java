package com.example.petarrange.service;

import com.example.petarrange.entity.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService {



    //添加用户
    public int addUser(String username,String password);

    //删除用户
    public int delUser(List<String> userList);

    //更新用户信息
    public int updateUser(User user);

    //查询所有用户信息
    public List<User> selectAllUser();

    //根据用户名查询用户
    public User findUserByName(String username);

    public List<User> findUsersByUsernameLike(String username);


}