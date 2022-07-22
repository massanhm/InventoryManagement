package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    /**
     * 指定日の在庫数検索画面を表示
     */
    @GetMapping("/search")
    public String displaySearch(Model model) {
        model.addAttribute("title", "在庫　照会");

        return "inventory/index";
    }
}

