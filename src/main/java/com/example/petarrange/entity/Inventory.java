package com.example.petarrange.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("inventory")
public class Inventory {
    private String itemid;
    private int qty;
}
