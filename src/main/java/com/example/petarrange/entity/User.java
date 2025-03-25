package com.example.petarrange.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.petarrange.annotation.EncryptField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@TableName("signon")
@AllArgsConstructor//有参构造
@NoArgsConstructor//无参构造
public class User {

    //id自增
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;
    @EncryptField
    private String password;


}
