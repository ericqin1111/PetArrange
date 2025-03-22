package com.example.petarrange.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("product")
public class Product {
    private String productid;
    private String category;
    private String name;
    private String descn;
    private String pic;

}
