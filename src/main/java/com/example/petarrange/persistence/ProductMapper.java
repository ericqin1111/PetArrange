package com.example.petarrange.persistence;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.petarrange.entity.Inventory;
import com.example.petarrange.entity.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProductMapper extends BaseMapper<Product> {
    @Select("select * from product where productid = #{productid}")
    public Product selectById(@Param("productid") String productid);

    @Insert("insert product (productid,category,name,descn) values (#{productid},#{category},#{name},#{descn})")
    public void Add(Product product);

    @Update("update product set "+
            "category=#{category}, "+
            "name=#{name}, "+
            "descn=#{descn} "+
            "where productid = #{productid}")
    public void update(Product product);
}
