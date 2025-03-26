package com.example.petarrange.handler;

import com.example.petarrange.utils.AESUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;

public class AESUtilsTypeHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        // 对存入数据库的字段进行加密
        String encryptedValue = AESUtils.encrypt(parameter);
        ps.setString(i, encryptedValue);
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        // 从数据库读取并解密字段
        String encryptedValue = rs.getString(columnName);
        return encryptedValue != null ? AESUtils.decrypt(encryptedValue) : null;
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        // 从数据库读取并解密字段
        String encryptedValue = rs.getString(columnIndex);
        return encryptedValue != null ? AESUtils.decrypt(encryptedValue) : null;
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        // 从存储过程读取并解密字段
        String encryptedValue = cs.getString(columnIndex);
        return encryptedValue != null ? AESUtils.decrypt(encryptedValue) : null;
    }
}