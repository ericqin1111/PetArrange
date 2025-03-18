package com.example.petarrange.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.petarrange.annotation.EncryptField;
import org.springframework.stereotype.Repository;

//@Data
@Repository
@TableName("admin")
public class Admin {
    @TableField("admin_id")
    @TableId
    private int adminId;
    @TableField("admin_name")
    private String adminName;
    @EncryptField
    private String password;


    public Admin() {
    }

    public Admin(int adminId, String adminName, String password) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.password = password;
    }

    /**
     * 获取
     * @return adminId
     */
    public int getAdminId() {
        return adminId;
    }

    /**
     * 设置
     * @param adminId
     */
    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    /**
     * 获取
     * @return adminName
     */
    public String getAdminName() {
        return adminName;
    }

    /**
     * 设置
     * @param adminName
     */
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "Admin{adminId = " + adminId + ", adminName = " + adminName + ", password = " + password + "}";
    }
}
