package com.example.petarrange.po;

import com.example.petarrange.mapper.CommodityDao;

import java.math.BigDecimal;

public class Commodity {
    private String id;
    private String name;
    private BigDecimal listprice;
    private BigDecimal unitcost;
    private int amount;
    private String url;
    private String category;
    private int isDelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getListprice() {
        return listprice;
    }

    public void setListprice(BigDecimal listprice) {
        this.listprice = listprice;
    }

    public BigDecimal getUnitcost() {
        return unitcost;
    }

    public void setUnitcost(BigDecimal unitcost) {
        this.unitcost = unitcost;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}
