package com.example.petarrange.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Commodity implements Serializable {

    private String itemId;

    private String productId;

    private String name;

    private BigDecimal listprice;

    private BigDecimal unitcost;

    private Integer quantity;

    private String url;

    private String category;

//    private Integer isDelete= 0;


}
