package com.example.petarrange.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("item_order")
public class ItemOrder implements Serializable  {

    @TableId("id")
    private  Integer id;  //
    @TableField("item_id")
    private String itemId; //商品id
    @TableField("user_name")
    private String userName; //用户id
    private String code; //订单编号
    @TableField("add_time")
    private Date addTime; //下单时间
    private Integer total; //购买数量
    @TableField("is_delete")
    private Integer isDelete;
    private Integer status; //0待发货 1已取消 2已发货 3已收货 4已评价
}