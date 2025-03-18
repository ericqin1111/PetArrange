package com.example.petarrange.Service;

import com.example.petarrange.base.BaseService;
import com.example.petarrange.base.BaseServiceImpl;
import com.example.petarrange.base.BaseDao;
import com.example.petarrange.mapper.CommodityDao;
import com.example.petarrange.po.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommodityServiceImpl extends BaseServiceImpl<Commodity> implements CommodityService{

    private CommodityDao commodityDao;
    @Override
    public BaseDao<Commodity> getBaseDao() {
        return commodityDao;
    }
}
