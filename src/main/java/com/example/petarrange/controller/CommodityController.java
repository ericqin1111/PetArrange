package com.example.petarrange.controller;

import com.example.petarrange.Service.impl.CategoryServiceImpl;
import com.example.petarrange.Service.impl.CommodityServiceImpl;
import com.example.petarrange.entity.Category;
import com.example.petarrange.entity.Commodity;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Commodity")
public class CommodityController {


    // controller层调用service层，service层调用dao层 mvc架构，我就不多说了
    //@Qualifier注解 别名
    @Autowired
    private CommodityServiceImpl commodityService;
    @Autowired
    private CategoryServiceImpl categoryService;


    @GetMapping("/allCategory")
    public String selectAllCategory(Model model){
        //使用业务层调用dao层查询出数据，通过model对象渲染到前台页面
        List<Category> categoryList = categoryService.selectAllCategory();
        model.addAttribute("CategoryList",categoryList);
        return "/commodity/Category";
    }

    @RequestMapping("/toAddCategory")
    public String toAddcategory(Model model,String name){
        Category category=new Category();
        model.addAttribute("category",category);
        //跳转到用户修改页面，同时将要修改的用户的信息传递过去
        return "/commodity/Add";
    }
    //接收添加用户表单的数据，进行正式的添加用户，添加完成后，重定向到所有用户页面
    @RequestMapping("/addCategory")//post is not supported
        public String addcategory(@ModelAttribute Category category,Model model){
        System.out.println(category);
        model.addAttribute(category);
        System.out.println(category);
        categoryService.insert(category.getCatid(),category.getName(),category.getDescn());
        return "redirect:/Commodity/allCategory";
    }

    //更新用户
    @RequestMapping("/toUpdateCategory")
    public String toUpdateCategory(Model model,String name){
        Category category=new Category();
        model.addAttribute("category",category);
        //跳转到用户修改页面，同时将要修改的用户的信息传递过去
        return "/commodity/Update";//?
    }

    //正式更新用户
    @RequestMapping("/updateCategory")
    public String updatecategory(@ModelAttribute Category category){

        System.out.println(category.toString());
        categoryService.update(category);
        System.out.println(category.getName());
        return "redirect:/Commodity/allCategory";
    }


    //删除
    @RequestMapping("/delCategory")
    public String delcategory(@Param("name")String name) {

        categoryService.delete(name);
        return "redirect:/Commodity/allCategory";
    }

    //查询用户 根据用户名查询
    @RequestMapping("/queryCategory")
    public  String querycategory(String categoryName,Model model){

        Category category= categoryService.findCategoryByName(categoryName);
        model.addAttribute(category);
        return "redirect:Commodity/allCategory";
    }

    @GetMapping("/FormPage")//有问题
    public String Form(HttpSession session, Model model){

        Object pageStr=session.getAttribute("page");
        int page = (pageStr != null) ? (int)pageStr : 1;
        int pageSize=5;
        int offset=(page-1)*pageSize;
        int totalRecord=0;
        int totalPages=0;

        List<Category> categoryList = new ArrayList<>();

        session.setAttribute("page",page);

        categoryList=categoryService.selectPageCategory(pageSize,offset);

        totalRecord=categoryService.count();
        totalPages=(int)Math.ceil(totalRecord/(double)pageSize);

        model.addAttribute("categoryList", categoryList);

        session.setAttribute("page",page);
        session.setAttribute("categoryList",categoryList);
        session.setAttribute("totalPages",totalPages);

        return "redirect:Commodity/allCategory";
    }

    @ResponseBody
    @GetMapping("/UpdateCategory")//?
    public Map<String, Object> updatecategory(@Param("page") int page, HttpSession session){

        Map<String,Object> map=new HashMap<>();

        int pageSize=5;
        System.out.println("page"+page);
        int offset=(page-1)*pageSize;
        int totalRecord=0;
        int totalPages=0;
        List<Category> categoryList;

        categoryList=categoryService.selectPageCategory(pageSize,offset);
        totalRecord=categoryService.count();
        totalPages=(int)Math.ceil(totalRecord/(double)pageSize);

        session.setAttribute("page",page);
        session.setAttribute("categoryList",categoryList);
        session.setAttribute("totalPages",totalPages);


        map.put("page",page);
        map.put("categoryList",categoryList);
        map.put("totalPages",totalPages);

        return map;
    }

    @PostMapping("/Search")
    public String searchcategoryLike(@RequestParam("keyword") String keyword,Model model){
        System.out.println(keyword);
        List<Category> categoryList;
        if(keyword==null) {
            categoryList = categoryService.selectAllCategory();
        }
        else {
            categoryList = categoryService.findCategoryBynameLike(keyword);
        }
        model.addAttribute("CategoryList",categoryList);
        return "/commodity/Category";
    }

    @GetMapping("/SomeCommodity")
    public String selectAllCommodity(@RequestParam("name") String name, Model model){
        //使用业务层调用dao层查询出数据，通过model对象渲染到前台页面
        System.out.println(name);
        List<Commodity> commodityList = commodityService.selectSomeCommodity(name);
        model.addAttribute("category", name);
        model.addAttribute("CommodityList",commodityList);
        return "/commodity/Commodity";
    }

    @RequestMapping("/toAddCommodity")
    public String toAddCommodity(Model model,@Param("category") String category){
        model.addAttribute("category" ,category);
        //跳转到用户修改页面，同时将要修改的用户的信息传递过去
        return "/commodity/Add2";
    }
    //接收添加用户表单的数据，进行正式的添加用户，添加完成后，重定向到所有用户页面
//    @RequestMapping("/addCommodity")//post is not supported
//    public String addCommodity(@ModelAttribute Commodity commodity,Model model){
//        model.addAttribute(commodity);
//        commodityService.insert(commodity.getId(),commodity.getName(),commodity.getListprice(),commodity.getUnitcost(),commodity.getAmount(),commodity.getUrl(),commodity.getCategory(),commodity.getIsDelete());
//        return "redirect:/Commodity/allCommodity";
//    }

    //更新用户
    @RequestMapping("/toUpdateCommodity")
    public String toUpdateCommodity(Model model,@Param("name")String name){
        System.out.println(name);
        Commodity commodity=commodityService.findCommodityByItemId(name);
        if (commodity == null) {
            throw new RuntimeException("商品不存在");
        }
        model.addAttribute("commodity",commodity);
        //跳转到用户修改页面，同时将要修改的用户的信息传递过去
        return "/commodity/Update";//?
    }

    //正式更新用户
    @RequestMapping("/updateCommodity")
    public String updateCommodity(@ModelAttribute("commodity") Commodity commodity){
        System.out.println("ssssssssssssssssssssss");
        commodityService.update(commodity);
        return "redirect:/Commodity/allCategory";
    }


    //删除
    @RequestMapping("/delCommodity")
    public String delCommodity(@Param("name")String name) {
        commodityService.delete(name);
        return "redirect:/Commodity/allCategory";
    }

    //查询用户 根据用户名查询
    @RequestMapping("/queryCommodity")
    public  String queryCommodity(String CommodityName,Model model){

        Commodity commodity= commodityService.findCommodityByItemId(CommodityName);
        model.addAttribute(commodity);
        return "redirect:Commodity/allCommodity";
    }

    @ResponseBody
    @GetMapping("/UpdateCommodity")//?
    public Map<String, Object> updateCommodity(@Param("page") int page, HttpSession session){

        Map<String,Object> map=new HashMap<>();

        int pageSize=5;
        System.out.println("page"+page);
        int offset=(page-1)*pageSize;
        int totalRecord=0;
        int totalPages=0;
        List<Commodity> CommodityList;

        CommodityList=commodityService.selectPageCommodity(pageSize,offset);
        totalRecord=commodityService.count();
        totalPages=(int)Math.ceil(totalRecord/(double)pageSize);

        session.setAttribute("page",page);
        session.setAttribute("CommodityList",CommodityList);
        session.setAttribute("totalPages",totalPages);


        map.put("page",page);
        map.put("CommodityList",CommodityList);
        map.put("totalPages",totalPages);

        return map;
    }

    @PostMapping("/addCommodity")
    public String addCommodity(@ModelAttribute Commodity commodity, Model model) throws IOException {
        System.out.println("ssssssssssssssssssssss");
        commodityService.addNew(commodity, commodity.getImage());
        return "redirect:/Commodity/allCategory";
    }

//    @PostMapping("/search")
//    public String searchCommodityLike(@RequestParam("keyword") String keyword,Model model){
//        System.out.println(keyword);
//        List<Commodity> commodityList;
//        if(keyword==null)commodityList=commodityService.selectAllCommodity();
//        else commodityList=commodityService.findCommodityBynameLike(keyword);
//        model.addAttribute("CommodityList",commodityList);
//        return "commodity/Commodity";
//    }
}