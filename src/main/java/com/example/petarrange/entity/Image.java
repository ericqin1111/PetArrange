package com.example.petarrange.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("images")
public class Image {
    private String itemid;
    @TableField("image_data")
    private byte[] image_data;
    @TableField("file_name")
    private String file_name;
}
