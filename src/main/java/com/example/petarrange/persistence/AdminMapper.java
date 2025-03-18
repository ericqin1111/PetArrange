package com.example.petarrange.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.petarrange.annotation.EncryptMethod;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper

public interface AdminMapper extends BaseMapper<Admin> {


    @EncryptMethod(value = {1}, enabledDecrypt = true)
    @Select("select * from admin where admin_name = #{adminName} And password = #{password}")
    public Admin selectByNameAndPsw(@Param("adminName") String adminName, @Param("password") String password);

    @Select("select * from admin where admin_name=#{adminName}")
    public Admin selectByadminName(@Param("adminName") String adminName);

    @EncryptMethod(value = {1}, enabledDecrypt = true)
    @Insert("insert into admin(admin_name,password) values (#{adminName},#{password})")
    public int insertToAdminNameAndPsd(@Param("adminName") String adminName,@Param("password") String password);
}
