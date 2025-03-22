package com.example.petarrange.persistence;

import com.example.petarrange.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.petarrange.entity.Category;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Mapper
@Repository
public interface CategoryMapper  {
    @Insert("insert into category(catid,name,descn) values (#{catid},#{name},#{descn})")
    public int insert(@Param("catid")String catid, @Param("name")String name, @Param("descn") String descn);

    @Delete("delete from category where name =#{name}")
    //删除用户  根据id
    public int delete(@Param("name") String name);

    @Update("UPDATE category SET " +//有问题
            "name = #{name}, " +
            "dsecn = #{dsecn}, " +
            "WHERE itemid = #{itemid}")
    //更新用户信息  根据id
    public int update(@Param("catid")String catid, @Param("name")String name, @Param("descn") String descn);

    @Select("select * from category")
    //查询所有用户信息
    public List<Category> selectAllCategory();


    @Select("select * from category where name=#{name}")
    //根据用户名查询用户
    public Category findCategoryByName(@Param("name") String name);

    @Select("SELECT catid,name,descn FROM category LIMIT #{limit} OFFSET #{offset}")
    List<Category> page(@Param("limit") int limit, @Param("offset") int offset);

    @Select("SELECT COUNT(*) FROM category")
    int getCount();

    @Select("SELECT * FROM category WHERE name LIKE CONCAT('%', #{name}, '%')")
    List<Category> findCategoryBynameLike(@Param("name")String name);
}
