package com.example.petarrange.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor//有参构造
@NoArgsConstructor//无参构造
public class User {

    private String userName;
    private String password;


    public String getUserName() {
        return userName;
    }
}
