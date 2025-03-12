package com.example.petarrange.service;
import com.example.petarrange.entity.User;
import com.example.petarrange.persistence.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService{


    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {

        this.userMapper = userMapper;
    }

    @Override
    public int addUser(User user) {

        return userMapper.addUser(user);
    }

    @Override
    public int delUser(User user) {
        return 0;
    }

    @Override
    public int updateUser(User user) {

        return userMapper.updateUser(user);
    }


    @Override
    public List<User> selectAllUser() {

        return userMapper.selectAllUser();
    }

    @Override
    public User findUserByName(String userName) {

        return userMapper.findUserByName(userName);
    }
}