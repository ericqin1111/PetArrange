package com.example.petarrange.service.impl;



import com.example.petarrange.persistence.ProductMapper;
import com.example.petarrange.service.CategoryService;
import com.example.petarrange.entity.Category;
import com.example.petarrange.persistence.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl  implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public int insert(String catid, String name,String descn) {
        return categoryMapper.insert(catid,name,descn);
    }

    @Override
    public int delete(String name) {//存疑
        System.out.println("sadddddddddddddddddddddddddddd");
        productMapper.delete(name);
        System.out.println("deleted product");
        return categoryMapper.delete(name);
    }



    @Override
    public List<Category> selectAllCategory() {
        List<Category> list=new ArrayList<>();
        list=categoryMapper.selectAllCategory();
        for(Category category:list){
            System.out.println(category.getCatid());

        }
        return list;
    }

    public int update(Category category){
        return categoryMapper.update(category.getCatid(),category.getName(),category.getDescn());
    }

    @Override
    public Category findCategoryByName(String name) {
        Category category=new Category();
        category=categoryMapper.findCategoryByName(name);
        return category;
    }

    @Override
    public List<Category> findCategoryBynameLike(String name) {
        return categoryMapper.findCategoryBynameLike(name);
    }

    public List<Category> selectPageCategory(int limit,int offset){
        return categoryMapper.page(limit,offset);
    }

    public int count(){
        System.out.println(categoryMapper.getCount());
        return categoryMapper.getCount();
    }

}
