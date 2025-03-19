package com.example.petarrange.service.impl;

import com.example.petarrange.entity.User;
import com.example.petarrange.persistence.UserMapper;
import com.example.petarrange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public int addUser(String username,String password) {
        return userMapper.addUser(username,password);
    }

    @Override
    public int delUser(List<String> userList) {
        int count=0;
        for(String username:userList){
            userMapper.delUser(username);
            count++;
        }
        return count;
    }



    @Override
    public List<User> selectAllUser() {
        List<User> list=new ArrayList<>();
        list=userMapper.selectAllUser();
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
        return userMapper.page(limit,offset);
    }

    public int count(){

        System.out.println(userMapper.getCount());
        return userMapper.getCount();
    }
}
