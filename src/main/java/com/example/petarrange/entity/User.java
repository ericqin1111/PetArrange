package com.example.petarrange.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.petarrange.annotation.EncryptField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data

@TableName("signon")
@AllArgsConstructor//有参构造
@NoArgsConstructor//无参构造



public class User {

    @TableId("username")
    private String username;
    @EncryptField
    private String password;






}
