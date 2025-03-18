package com.example.petarrange.mapper;

import com.example.petarrange.base.BaseDao;
import com.example.petarrange.po.Commodity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;
@Mapper
public interface CommodityDao extends BaseDao<Commodity>{
        // 新增商品
        @Insert("INSERT INTO Commodity (name, id, listprice, url, isDeletes,amount,description) " +
                "VALUES (#{name}, #{id}, #{listprice}, #{url}, #{isDelete},#{amount},#{description}")
        @Options(useGeneratedKeys = true, keyProperty = "id")
        int insert(Commodity Commodity);

        // 根据ID删除商品
        @Delete("DELETE FROM Commodity WHERE id = #{id}")
        int deleteById(Long id);

        // 更新商品信息
        @Update("UPDATE Commodity SET " +
                "name = #{name}, " +
                "description = #{description}, " +
                "listprice = #{listprice}, " +
                "url = #{url}, " +
                "isDelete = #{isDelete}, " +
                "WHERE id = #{id}")
        void update(Commodity Commodity);

        // 根据ID查询商品
        @Select("SELECT * FROM Commodity WHERE id = #{id}")
        Commodity selectById(Long id);

        // 分页查询商品列表
        @Select("SELECT * FROM Commodity WHERE name LIKE CONCAT('%', #{keyword}, '%') ORDER BY create_time DESC")
        List<Commodity> selectByPage(@Param("keyword") String keyword);
}
