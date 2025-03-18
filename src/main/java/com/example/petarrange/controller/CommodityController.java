package com.example.petarrange.controller;

import com.example.petarrange.Service.CommodityService;
import com.example.petarrange.base.BaseController;
import com.example.petarrange.po.Commodity;
import com.example.petarrange.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/Commodity")
public class CommodityController extends BaseController {

    @Autowired
    private CommodityService commodityService;
    
    @RequestMapping("/findBySql")
    public String findBySql(Model model, Commodity commodity){
        String sql="Select * from Commodity where isDelete = 0 and pid is null order by id";
        if(!isEmpty(commodity.getName())){
            sql+="and name like '%" + commodity.getName()+"%'";
        }
        sql+="order by id";
        Pager<Commodity> pagers=commodityService.findBySqlReturnEntity(sql);
        model.addAttribute("pagers",pagers);
        model.addAttribute("obj",commodity);
        return "Commodity";
    }
    @RequestMapping("/Add")
    public String add(Model model){
        return "/Add";
    }
    @RequestMapping("/Add2")
    public String add2(Model model){
        return "/Add2";
    }
    @GetMapping("/index.html")
    public String indexPage() {
        return "index.html"; // 对应templates/Commodity/list.html
    }
    @RequestMapping("/Commodity")
    // 跳转到商品列表页（直接返回视图名称）
    @GetMapping("/Commodity")
    public String CommodityPage() {
        return "Commodity"; // 对应templates/Commodity/list.html
    }
    @GetMapping("/Category")
    public String CategoryPage() {
        return "Category"; // 对应templates/Commodity/list.html
    }
    // 提交新增商品
    @PostMapping
    public String createCommodity(Commodity Commodity) {
        commodityService.insert(Commodity);
        return "/Commodity";
    }

    // 删除商品
    @PostMapping("/{id}/delete")
    public String deleteCommodity(Long id) {
        commodityService.deleteById(id);
        return "/Commodity";
    }

}
