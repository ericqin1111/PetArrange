package com.example.petarrange.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.petarrange.annotation.EncryptField;
import lombok.Data;
import org.springframework.stereotype.Repository;

@Data
@Repository
@TableName("admin")
public class Admin {
    @TableField("admin_id")
    private int adminId;
    @TableField("admin_name")
    private String adminName;
    @EncryptField
    private String password;
}
