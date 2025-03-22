package com.example.petarrange.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;

@Data
@TableName("category")
public class Category {
    @TableId("catid")
    private String catid;
    @TableField("name")
    private String name;
    @TableField("descn")
    private String descn;


}
