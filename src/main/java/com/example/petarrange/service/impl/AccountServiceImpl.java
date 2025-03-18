package com.example.petarrange.service.impl;

import com.example.petarrange.persistence.AdminMapper;
import com.example.petarrange.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    AdminMapper adminMapper;
    @Override
    public boolean isAdiminExist(String adminName, String password) {
        boolean isExist=false;
        Admin admin=adminMapper.selectByNameAndPsw(adminName,password);
        if(admin!=null){
            isExist=true;
        }
        return isExist;


    }

    @Override
    public boolean isAdminNameExist(String adminName) {
        boolean isExist=true;
        Admin admin=adminMapper.selectByadminName(adminName);
        if(admin==null){
            isExist=false;
        }
        return isExist;
    }

    @Override
    public void insertAdmin(String adminName, String password) {
        adminMapper.insertToAdminNameAndPsd(adminName,password);
    }
}
