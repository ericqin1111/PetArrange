package com.example.petarrange.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("item")
public class Item {
    private String itemid;
    private String productid;
    private BigDecimal listprice;
    private BigDecimal unitcost;
    private int supplier;
    private String status;
    private String attr1;
    private String attr2;
    private String attr3;
    private String attr4;
    private String attr5;
}
