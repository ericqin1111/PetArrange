package com.example.petarrange.persistence;


import com.example.petarrange.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    //添加用户
    public int addUser(User user);

    //删除用户  根据id
    public int delUser(@Param("username") String username);

    //更新用户信息  根据id
    public int updateUser(User user);


    //查询所有用户信息
    public List<User> selectAllUser();

    //根据用户名查询用户
    public User findUserByName(@Param("userName") String userName);
}
