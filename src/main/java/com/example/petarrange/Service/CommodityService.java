package com.example.petarrange.Service;

import com.example.petarrange.entity.Commodity;

import java.math.BigDecimal;
import java.util.List;

public interface CommodityService {
    public int insert(String id, String name, BigDecimal listprice, BigDecimal unitcode, int amount, String url, String category, int isDelete);
    //删除用户  根据id
    public void delete(String name);

    List<Commodity> selectSomeCommodity(String name);

    //更新用户信息  根据id
    public int update(Commodity commodity);
    //查询所有用户信息
    public List<Commodity> selectAllCommodity();
    //根据用户名查询用户
    public Commodity findCommodityByItemId(String ItemId);

    public List<Commodity> findCommodityBynameLike(String name);
    public void addNew(Commodity commodity);
}
