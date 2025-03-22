package com.example.petarrange.persistence;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.petarrange.entity.Image;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ImagesMapper extends BaseMapper<Image> {

    
    @Select("select file_name from images where itemid = #{itemid}")
    public String getNameByItemid(String itemid);

    @Select("select image_data from images where itemid = #{itemid}")
    public byte[][] getDataByItemid(String itemid);

    @Select("select * from images where itemid = #{itemid}")
    public Image[] getImageByItemid(String itemid);

}
