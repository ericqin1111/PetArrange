package com.example.petarrange.service;

import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    public boolean isAdiminExist(String adminName, String password);

    public boolean isAdminNameExist(String adminName);

    public void insertAdmin(String AdminName,String password);
}
