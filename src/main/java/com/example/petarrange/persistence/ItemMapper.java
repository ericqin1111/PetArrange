package com.example.petarrange.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.petarrange.entity.Inventory;
import com.example.petarrange.entity.Item;
import com.example.petarrange.entity.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ItemMapper extends BaseMapper<Item> {

    @Delete("DELETE from item where itemid = #{itemId}")
    public void delete(@Param("itemId") String itemid);

    @Select("select * from item where itemid = #{itemid}")
    public Item selectById(@Param("itemid") String itemid);

    @Insert("insert item (itemid,productid,listprice,unitcost,supplier,status,attr1,attr2,attr3,attr4,attr5)" +
            " values (#{itemid},#{productid},#{listprice},#{unitcost},#{supplier},#{status},#{attr1},#{attr2},#{attr3},#{attr4},#{attr5})")
    public void Add(Item item);

    @Update("update item set "+
            "productid=#{productid}, "+
            "listprice=#{listprice}, "+
            "unitcost=#{unitcost}, "+
            "supplier=#{supplier}, "+
            "status=#{status}, "+
            "attr1=#{attr1}, "+
            "attr2=#{attr2}, "+
            "attr3=#{attr3}, "+
            "attr4=#{attr4}, "+
            "attr5=#{attr5} "+
            "where itemid=#{itemid}")
    public void update(Item item);
}
