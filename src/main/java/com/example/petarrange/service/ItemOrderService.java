package com.example.petarrange.service;

import com.example.petarrange.entity.ItemOrder;
import com.example.petarrange.entity.ItemOrderArray;
//import com.example.petarrange.utils.Pager;
import org.apache.ibatis.annotations.Param;

public interface ItemOrderService{

    public ItemOrderArray getAllOrders();

    public ItemOrderArray getOrderByCode(String code);

//    public Pager<ItemOrder> findBySqlReturnEntity(@Param("sql")String sql);

    public ItemOrder findById(int id);

    public void updateById(ItemOrder itemOrder);

    public void deleteById(int id);

    public void shipOrder(Long id);
}
