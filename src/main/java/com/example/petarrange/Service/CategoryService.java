package com.example.petarrange.service;


import com.example.petarrange.entity.Category;
import com.example.petarrange.entity.Category;

import java.math.BigDecimal;
import java.util.List;

public interface CategoryService {
    public int insert(String catid, String name,String descn);
    //删除用户  根据id
    public int delete(String name);
    //更新用户信息  根据id
    public int update(Category category);
    //查询所有用户信息
    public List<Category> selectAllCategory();
    //根据用户名查询用户
    public Category findCategoryByName(String name);

    public List<Category> findCategoryBynameLike(String name);
}
