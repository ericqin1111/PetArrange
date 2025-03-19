package com.example.petarrange.controller;

import com.example.petarrange.entity.ItemOrder;
import com.example.petarrange.entity.ItemOrderArray;
import com.example.petarrange.service.ItemOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/itemOrder")
public class ItemOrderController  {
    private static final Logger logger = LoggerFactory.getLogger(ItemOrderController.class);

    @Autowired
    private ItemOrderService itemOrderService;


    @RequestMapping("/orderForm0")
    public String orderForm0(Model model) {
        ItemOrderArray itemOrderArray = itemOrderService.getAllOrders();
        System.out.println(itemOrderArray);
        model.addAttribute("orderArray", itemOrderArray);

        return "/order/itemOrder0";
    }

    @GetMapping("/orderForm")
    public String orderForm(Model model) {
        ItemOrderArray itemOrderArray = itemOrderService.getAllOrders();
        System.out.println(itemOrderArray);
        model.addAttribute("orderArray", itemOrderArray);

        return "/order/itemOrder";
    }


    @RequestMapping("/orderForm/{code}")
    public String findByCode(@PathVariable String code, Model model) {
        if (code != "null") {
            System.out.println("2222");
            ItemOrderArray itemOrderArray = itemOrderService.getOrderByCode(code);
            model.addAttribute("orderArray", itemOrderArray);
            return "/order/itemOrder";
        }
        else {
            System.out.println("111");
            ItemOrderArray itemOrderArray = new ItemOrderArray();
            model.addAttribute("orderArray", itemOrderArray);
            return "/order/itemOrder";
        }
//        // 分页查询
//        String sql = "select * from item_order";
//        if (!(isEmpty(itemOrder.getCode()))) {
//            sql += " and code like '%" + itemOrder.getCode() + "%'";
//        }
//        sql += " order by id desc";
//        logger.info("Generated SQL: {}", sql); // 添加日志输出SQL查询语句
//        logger.info("ItemOrder code: {}", itemOrder.getCode());
//        Pager<ItemOrder> pagers = itemOrderService.findBySqlReturnEntity(sql);
//
//        // 检查 pagers 和 datas 是否为 null
//        if (pagers == null || pagers.getDatas() == null) {
//            pagers = new Pager<>();
//            pagers.setDatas(Collections.emptyList());
//        }
//
//
//        // 过滤掉 isDelete 为 1 的订单
//        List<ItemOrder> filteredOrders = pagers.getDatas().stream()
//                .filter(order -> order.getIsDelete() == 0)
//                .collect(Collectors.toList());
//
//        // 更新 Pager 对象
//        pagers.setDatas(filteredOrders);
//        pagers.setTotal(filteredOrders.size());
//
//        logger.info("Query result: {}", pagers); // 添加日志输出查询结果
//        model.addAttribute("pagers", pagers);
//        // 存储查询条件
//        model.addAttribute("obj", itemOrder);

    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable int id, Model model) {
        ItemOrder itemOrder = itemOrderService.findById(id);
        if (itemOrder == null) {
            model.addAttribute("error", "订单不存在");
            return "error";
        }
        if (itemOrder.getStatus() == 2) {
            model.addAttribute("success", "订单已发货");
        }
        else {
            model.addAttribute("success", null);
        }
        model.addAttribute("itemOrder", itemOrder);
        return "/order/itemOrderDetail0";
    }

    @PostMapping("/update")
    public String update(ItemOrder itemOrder, Model model) {
        itemOrderService.updateById(itemOrder);
        model.addAttribute("success", "订单更新成功");
        return "redirect:/itemOrder/detail/" + itemOrder.getId();
    }

    @PostMapping("/delete")
    public String delete(@RequestParam int id, Model model) {
        itemOrderService.deleteById(id);
        return "redirect:/itemOrder/orderForm";
    }

    @PostMapping("/ship")
    public String ship(@RequestParam Long id, Model model) {
        itemOrderService.shipOrder(id);


        return "redirect:/itemOrder/detail/" + id;
    }
}