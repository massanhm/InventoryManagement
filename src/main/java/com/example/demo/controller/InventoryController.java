package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Form.InventorySearchReq;
import com.example.demo.Service.InventoryService;
import com.example.demo.entity.Inventory;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    /**
     * 指定日の在庫数検索画面を表示
     */
    @GetMapping("/search")
    public String displaySearch(Model model) {

        model.addAttribute("title", "在庫　照会");

        return "inventory/index";
    }

    /*
     * 在庫検索
     * @return 在庫一覧表示
     */
    @PostMapping("/id_search")
    public String search(
            @Validated @ModelAttribute InventorySearchReq inventorySearchReq,
            Model model
            ) {

        java.sql.Date sqlDate = java.sql.Date.valueOf(inventorySearchReq.getReportDate());

        List<Inventory> list = inventoryService.findByDate(sqlDate);


        model.addAttribute("list", list);
        model.addAttribute("title", "在庫一覧");

        return "inventory/index";
    }




}


