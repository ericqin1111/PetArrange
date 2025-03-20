package com.example.petarrange.service.impl;

import com.example.petarrange.entity.ItemOrder;
import com.example.petarrange.entity.ItemOrderArray;
import com.example.petarrange.persistence.ItemOrderMapper;
import com.example.petarrange.service.ItemOrderService;
//import com.example.petarrange.utils.Pager;
//import com.example.petarrange.utils.SystemContext;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemOrderServiceImpl implements ItemOrderService {

    @Autowired
    private ItemOrderMapper itemOrderMapper;


    @Override
    public ItemOrderArray getAllOrders() {
        ItemOrderArray itemOrderArray = new ItemOrderArray();
        itemOrderArray.setItemOrders(itemOrderMapper.findAllOrders());
        return itemOrderArray;
    }

    @Override
    public ItemOrderArray getOrderByCode(String code) {
        ItemOrderArray itemOrderArray = new ItemOrderArray();
        itemOrderArray.setItemOrders(itemOrderMapper.findOrdersByCode(code));
        return itemOrderArray;
    }


    @Override
    public ItemOrder findById(int id) {
        return itemOrderMapper.selectById(id);
    }

    @Override
    public void updateById(ItemOrder itemOrder) {
        itemOrderMapper.updateById(itemOrder);
    }

    @Override
    public void deleteById(int id) {
        itemOrderMapper.deleteById(id);
    }

    @Override
    public void shipOrder(Long id) {
        ItemOrder itemOrder = itemOrderMapper.selectById(id);
        if (itemOrder != null) {
            itemOrder.setStatus(2); // 假设2表示已发货
            itemOrderMapper.updateById(itemOrder);
        }
    }


//    public Pager<ItemOrder> findBySqlReturnEntity(String sql) {
////        /**
////         * 执行分页
////         */
////        Integer pageSize = SystemContext.getPageSize();
////        Integer pageOffset = SystemContext.getPageOffset();
////        if(pageOffset==null||pageOffset<0) pageOffset = 0;
////        if(pageSize==null||pageSize<0) pageSize = 15;
////        String order = SystemContext.getOrder();
////        String sort = SystemContext.getSort();
////        Integer pageNum = null;
////        if(pageOffset == 0){
////            pageNum = 1;
////        }else{
////            pageNum = pageOffset/pageSize+1;
////        }
////        PageHelper.startPage(pageNum, pageSize);
////        Pager<ItemOrder> pages = new Pager<ItemOrder>(this.itemOrderMapper.findBySqlReturnEntity(sql));
////        return pages;
//    }
}
