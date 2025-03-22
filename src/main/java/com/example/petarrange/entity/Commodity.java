package com.example.petarrange.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

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

    private MultipartFile image;

//    private Integer isDelete= 0;


}
