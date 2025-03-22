package com.example.petarrange.persistence;

import com.example.petarrange.entity.Commodity;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface CommodityMapper{
    @Insert("insert into commodity(id,name,listprice,unitcost,amount,url,category,isDelete) values (#{id},#{name},#{listprice},#{unitcost},#{amount},#{url},#{category},#{isDelete})")
    public int insert(@Param("id")String id,@Param("name")String name,@Param("listprice")BigDecimal listprice,@Param("unitcost")BigDecimal unitcost,@Param("amount")Integer amount,@Param("url")String url,@Param("category")String category,@Param("isDelete")Integer isDelete);

    @Delete("delete from item, inventory where itemid =#{name}")
    //删除用户  根据id
    public int delete(@Param("name") String name);

    @Update("UPDATE commodity SET " +
            "listprice = #{listprice}, " +
            "unitcost = #{unitcost}, " +
            "amount = #{amount}, " +
            "WHERE itemId = #{itemId}")
    int update(Commodity commodity);

    @Select("select * from commodity")
    //查询所有用户信息
    public List<Commodity> selectAllCommodity();


    @Select("select "+
            "i.itemid as itemId," +
            "i.productid as productId," +
            "name as name," +
            "listprice," +
            "unitcost," +
//            "status," +
//            "attr1 as attribute1," +
//            "attr2 as attribute2," +
//            "attr3 as attribute3," +
//            "attr4 as attribute4," +
//            "attr5 as attribute5, " +
            "qty as quantity, " +
            "descn as url, " +
            "category as category " +
            "from item i, inventory v, product p " +
            "where p.productid = i.productid " +
            "and i.itemid = v.itemid " +
            "and  i.itemId = #{itemId}")
    Commodity findCommodityByItemId(@Param("itemId") String itemId);

    @Select("SELECT id,name,listprice,unitcost,amount,url,category,isDelete FROM commodity LIMIT #{limit} OFFSET #{offset}")
    List<Commodity> page(@Param("limit") int limit, @Param("offset") int offset);

    @Select("SELECT COUNT(*) FROM commodity")
    int getCount();

    @Select("SELECT * FROM commodity WHERE name LIKE CONCAT('%', #{name}, '%')")
    List<Commodity> findCommodityBynameLike(@Param("name")String name);

    @Select("select " +
            "i.itemid as itemId," +
            "i.productid as productId," +
            "name as name," +
            "listprice," +
            "unitcost," +
//            "status," +
//            "attr1 as attribute1," +
//            "attr2 as attribute2," +
//            "attr3 as attribute3," +
//            "attr4 as attribute4," +
//            "attr5 as attribute5, " +
            "qty as quantity, " +
            "descn as url, " +
            "category as category " +
            "from item i, inventory v, product p " +
            "where p.productid = i.productid " +
            "and i.itemid = v.itemid " +
            "and  p.category = #{category}  ")
    List<Commodity> findCommodityByCategory(@Param("category") String category);
}
