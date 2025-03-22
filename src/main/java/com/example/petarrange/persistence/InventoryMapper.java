package com.example.petarrange.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.petarrange.entity.Inventory;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface InventoryMapper extends BaseMapper<Inventory> {
    @Delete("delete from inventory where itemid = #{itemId}")
    public void delete(@Param("itemId") String itemid);

    @Select("select * from inventory where itemid = #{itemid}")
    public Inventory selectById(@Param("itemid") String itemid);

    @Insert("insert inventory (itemid,qty) values (#{itemid},#{qty})")
    public void Add(Inventory inventory);

    @Update("update inventory set "+
            "qty=#{qty} "+
            "where itemid=#{itemid}")
    public void update(Inventory inventory);
}
