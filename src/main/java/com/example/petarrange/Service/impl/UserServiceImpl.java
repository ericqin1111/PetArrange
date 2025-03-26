package com.example.petarrange.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.petarrange.annotation.EncryptMethod;

import com.example.petarrange.entity.User;
import com.example.petarrange.persistence.UserMapper;
import com.example.petarrange.service.UserService;
import com.example.petarrange.utils.AESUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.logging.*;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;


    Logger logger=Logger.getLogger("UserServiceImpl");

    @Override
    @EncryptMethod
    public int addUser(String username,String password) {
        logger.info(password);
        logger.info(username);
        System.out.println("username"+username);
        System.out.println("password"+password);
        return userMapper.addUser(username,password);
    }

    @Override
    public int delUser(List<String> userList) {
        int count=0;
        for(String username:userList){
//            userMapper.delUser(username);
            count++;
        }
        return count;
    }



    @Override
    public List<User> selectAllUser() {
        List<User> list=new ArrayList<>();
        list=userMapper.selectAllUser();
        for(User user:list){
            user.setPassword(AESUtils.decrypt(user.getPassword()));
            System.out.println(user.getPassword());
        }
        return list;
    }

    public int updateUser(User user){

        return userMapper.updateUser(user.getUsername(),user.getPassword());
    }

    @Override
    public User findUserByName(String username) {
        User user=new User();
        user=userMapper.findUserByName(username);
        return user;
    }

    @Override
    public List<User> findUsersByUsernameLike(String username) {
        return userMapper.findUsersByUsernameLike(username);
    }

    public List<User> selectPageUser(int limit,int offset){
        List<User> userList= userMapper.page(limit,offset);
        return userList;
    }

    public int countNum(){
        System.out.println("总数"+userMapper.getCount());
        return userMapper.getCount();
    }
}
