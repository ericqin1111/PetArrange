package com.example.petarrange.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.petarrange.entity.ItemOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ItemOrderMapper extends BaseMapper<ItemOrder> {

    @Select("select * from item_order where is_delete = 0")
    List<ItemOrder> findAllOrders();

    @Update("        UPDATE item_order" +
            "        SET is_delete = 1" +
            "        WHERE id = #{id}")
    void deleteById(@Param("id") int id);  //软删除

    @Select("select * from item_order where code = #{code} and is_delete = 0")
    List<ItemOrder> findOrdersByCode(String code);
}