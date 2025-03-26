package com.example.petarrange.service.impl;

import com.example.petarrange.service.CommodityService;
import com.example.petarrange.entity.*;
import com.example.petarrange.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service("CommodityService")
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    private CommodityMapper commodityMapper;
    @Autowired
    private InventoryMapper inventoryMapper;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ImagesMapper imagesMapper;

    @Override
    public int insert(String id, String name, BigDecimal listprice, BigDecimal unitcode, int amount, String url, String category, int isDelete) {
        return commodityMapper.insert(id, name, listprice, unitcode, amount, url, category, isDelete);
    }

    @Override
    public void delete(String name) {//存疑
        itemMapper.delete(name);
        inventoryMapper.delete(name);
    }

    @Override
    public List<Commodity> selectAllCommodity() {
        List<Commodity> list = new ArrayList<>();
        list = commodityMapper.selectAllCommodity();
//        for(Commodity commodity:list){
//            System.out.println(commodity.getId());
//            System.out.println(commodity.getName());
//            System.out.println(commodity.getListprice());
//            System.out.println(commodity.getUnitcost());
//            System.out.println(commodity.getAmount());
//            System.out.println(commodity.getUrl());
//            System.out.println(commodity.getCategory());
//            System.out.println(commodity.getIsDelete());
//        }
        return list;
    }

    @Override
    public List<Commodity> selectSomeCommodity(String category) {
        List<Commodity> list = new ArrayList<>();
        list = commodityMapper.findCommodityByCategory(category);
        for (Commodity commodity : list) {

        }
        return list;
    }

    @Override
    public int update(Commodity commodity) {
        //itemid在数据表存在不添加
            Inventory inventory = inventoryMapper.selectById(commodity.getItemId());
            inventory.setQty(commodity.getQuantity());
            inventoryMapper.update(inventory);

        //productid在数据表存在不添加
            Product product = productMapper.selectById(commodity.getProductId());
            product.setCategory(commodity.getCategory());
            product.setName(commodity.getName());
            product.setDescn(commodity.getUrl());
            productMapper.update(product);

        //itemid在数据表存在不添加
            Item item = itemMapper.selectById(commodity.getItemId());
            item.setProductid(commodity.getProductId());
            item.setListprice(commodity.getListprice());
            item.setUnitcost(commodity.getUnitcost());
            item.setUnitcost(commodity.getUnitcost());
            item.setStatus("P");
            item.setSupplier(2);
            item.setAttr1("aaaaa");
            item.setAttr2(null);
            item.setAttr3(null);
            item.setAttr4(null);
            itemMapper.update(item);
        return 1;
    }



//    public int update(Commodity commodity){
//        return commodityMapper.update(commodity.getId(),commodity.getName(),commodity.getListprice(),commodity.getUnitcost(),commodity.getAmount(),commodity.getUrl(),commodity.getCategory(),commodity.getIsDelete());
//    }

    @Override
    public Commodity findCommodityByItemId(String ItemId) {
        return commodityMapper.findCommodityByItemId(ItemId);
    }

    @Override
    public List<Commodity> findCommodityBynameLike(String name) {
        return commodityMapper.findCommodityBynameLike(name);
    }

    public List<Commodity> selectPageCommodity(int limit,int offset){
        return commodityMapper.page(limit,offset);
    }

    public int count(){
        System.out.println(commodityMapper.getCount());
        return commodityMapper.getCount();
    }

    @Override
    public void addNew(Commodity commodity, MultipartFile file) throws IOException {
        Inventory inventory = new Inventory();
        Item item = new Item();
        Product product = new Product();

        if (!file.isEmpty()) {
            Image image = new Image();
            image.setFile_name("q1");
            image.setImage_data(file.getBytes());
            image.setItemid(commodity.getItemId());
            imagesMapper.insert(image);
        }

        //itemid在数据表存在不添加
        if(inventoryMapper.selectById(commodity.getItemId())==null){
        inventory.setItemid(commodity.getItemId());
        inventory.setQty(commodity.getQuantity());
        inventoryMapper.Add(inventory);
        }

        //productid在数据表存在不添加
        if(productMapper.selectById(commodity.getProductId())==null){
        product.setCategory(commodity.getCategory());
        product.setProductid(commodity.getProductId());
        product.setName(commodity.getName());
        product.setDescn(commodity.getUrl());
        productMapper.Add(product);
        }

        //itemid在数据表存在不添加
        if(itemMapper.selectById(commodity.getItemId())==null){
            System.out.println("sadasdasdsaaszasa");
            item.setItemid(commodity.getItemId());
            item.setProductid(commodity.getProductId());
            item.setListprice(commodity.getListprice());
            item.setStatus("P");
            item.setSupplier(2);
            item.setAttr1("aaaaa");
            item.setAttr2(null);
            item.setAttr3(null);
            item.setAttr4(null);
            itemMapper.Add(item);
        }
    }
}
