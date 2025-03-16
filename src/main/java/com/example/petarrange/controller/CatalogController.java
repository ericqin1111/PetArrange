package com.example.petarrange.controller;

import com.example.petarrange.persistence.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

    @GetMapping("/main")
    public String main(){
        return "/catalog/main";
    }
}
