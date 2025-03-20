package com.example.petarrange.persistence;


import com.example.petarrange.annotation.EncryptField;
import com.example.petarrange.annotation.EncryptMethod;
import com.example.petarrange.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface UserMapper {

    @EncryptMethod
    //添加用户
    @Insert("insert into signon(username,password) values (#{username},#{password})")
    public int addUser(@Param("username")String username,@Param("password")String password);



    @Delete("delete from signon where username =#{username}")
    //删除用户  根据id
    public int delUser(@Param("username") String username);


    @EncryptMethod
    @Update("update signon SET password=#{password} where username=#{username}")
    //更新用户信息  根据id
    public int updateUser(@Param("username") String username,@Param("password")String password);


    @Select("select * from signon")
    //查询所有用户信息
    public List<User> selectAllUser();


    @Select("select * from signon where username=#{username}")
    //根据用户名查询用户
    public User findUserByName(@Param("username") String username);


//    @Select("SELECT username,password FROM signon ORDER BY username DESC LIMIT #{limit} OFFSET #{offset}")

    @Select("SELECT username,password FROM signon LIMIT #{limit} OFFSET #{offset}")
    List<User> page(@Param("limit") int limit, @Param("offset") int offset);

    @Select("SELECT COUNT(*) FROM signon")
    int getCount();

    @Select("SELECT * FROM signon WHERE username LIKE CONCAT('%', #{username}, '%')")
    List<User> findUsersByUsernameLike(@Param("username")String username);
}
