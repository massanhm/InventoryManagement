package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.Form.InventoryForm;
import com.example.demo.Form.InventorySearchReq;
import com.example.demo.Service.InventoryService;
import com.example.demo.Service.ProductService;
import com.example.demo.entity.Inventory;
import com.example.demo.entity.Product;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;
    private final ProductService productService;

    /**
     * 指定日の在庫数検索画面を表示
     */
    @GetMapping("/search")
    public String displaySearch(
            InventorySearchReq inventorySearchReq,
            Model model
            ) {

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
            BindingResult result,
            Model model

            ) {

        if (result.hasErrors()) {
            model.addAttribute("inventorySearchReq", inventorySearchReq);
            model.addAttribute("title", "在庫　照会");

            return "inventory/index";
        }

        java.sql.Date sqlDate = java.sql.Date.valueOf(inventorySearchReq.getReportDate());

        List<Inventory> list = inventoryService.findByDate(sqlDate);

        model.addAttribute("list", list);
        model.addAttribute("title", "在庫一覧");

        return "redirect:/inventory/index";
    }

    /*
     * 在庫情報の追加ページを表示する
     */
    @GetMapping("/form")
    public String form(InventoryForm inventoryForm, Model model) {
        List<Product> prodList = productService.findAll();
        model.addAttribute("prodList", prodList);
        model.addAttribute("title", "在庫数登録画面");
       return "inventory/form";
    }

    /*
     * 在庫情報を一件挿入
     */
    @PostMapping("/insert")
    public String insert(
            @Validated @ModelAttribute InventoryForm inventoryForm,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("inventoryForm", inventoryForm);
            model.addAttribute("title", "在庫数登録画面");

            return "inventory/form";
        }

        redirectAttributes.addFlashAttribute("complete", "登録完了しました。");
        return "redirect:/inventory/form";

    }

    private Inventory makeInventory(InventoryForm inventoryForm) {
        Inventory inventory = new Inventory();

        inventory.setProductsId(inventoryForm.getProductId());
        inventory.setQuantity(inventoryForm.getQuantity());
        inventory.setReportDate(inventoryForm.getReportDate());

        return inventory;
    }
}


