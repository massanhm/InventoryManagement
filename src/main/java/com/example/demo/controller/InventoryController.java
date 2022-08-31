package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
     * 日付による在庫検索
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

        return "inventory/index";
    }

    /*
     * 在庫情報の追加ページを表示する
     */
    @GetMapping("/form")
    public String form(InventoryForm inventoryForm, Model model) {
        List<Product> info = productService.findAll();

        model.addAttribute("inventoryForm", inventoryForm);
        model.addAttribute("prodList", info);
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
            List<Product> info = productService.findAll();

            model.addAttribute("inventoryForm", inventoryForm);
            model.addAttribute("prodList", info);
            model.addAttribute("title", "在庫数登録画面");

            return "inventory/form";
        }

        Inventory inventory = makeInventory(inventoryForm, 0);

        inventoryService.create(inventory);
        model.addAttribute("title", "在庫数登録画面");
        redirectAttributes.addFlashAttribute("complete", "登録完了しました。");
        return "redirect:/inventory/form";

    }

    /**
     *一件在庫情報を取得し、編集ページを表示する
     */
    @GetMapping("/{id}")
    public String edit(
            @PathVariable("id") int id,
            InventoryForm inventoryForm,

            Model model) {

        Optional<Inventory> inventoryOpt = inventoryService.getInventory(id);

        Optional<InventoryForm> inventoryFormOpt = inventoryOpt.map(t -> makeInventoryForm(t));

        if (inventoryFormOpt.isPresent()) {
            inventoryForm = inventoryFormOpt.get();
        }

        model.addAttribute("inventoryForm", inventoryForm);

        List<Product> info = productService.findAll();
        model.addAttribute("prodList", info);
        model.addAttribute("Id", id);
        model.addAttribute("title", "在庫情報　編集画面");

        return "inventory/edit";
    }

    /**
     *一件在庫idを取得し、更新する
     */
    @PostMapping("/update")
    public String update(
        @RequestParam("id") int id,
        @Validated @ModelAttribute InventoryForm inventoryForm,
        BindingResult result,
        Model model,
        RedirectAttributes redirectAttributes
        ) {

        if (result.hasErrors()) {
            model.addAttribute("inventoryForm", inventoryForm);

            List<Product> info = productService.findAll();
            model.addAttribute("prodList", info);
            model.addAttribute("Id", id);
            model.addAttribute("title", "在庫情報　編集画面");

            return "inventory/edit";
        }

        Inventory inventory = makeInventory(inventoryForm, id);

        inventoryService.update(inventory);
        redirectAttributes.addFlashAttribute("complete", "変更が完了しました");

        return "redirect:/inventory/search";

    }

    /*
     * 一件在庫idを取得し、一件のデータを削除
     */
    @PostMapping("/delete")
    public String delete(
        @RequestParam("id") int id,
        Model model
        ) {

        inventoryService.deleteById(id);
        return "redirect:/inventory/search";
    }

    /*
     * InventoryFormのデータをInventoryに入れて返す
     */
    private Inventory makeInventory(InventoryForm inventoryForm, int id) {
        Inventory inventory = new Inventory();

        inventory.setId(id);
        inventory.setProductsId(inventoryForm.getProductId());
        inventory.setQuantity(inventoryForm.getQuantity());
        inventory.setReportDate(java.sql.Date.valueOf(inventoryForm.getReportDate()));

        return inventory;
    }

    /*
     * InventoryのデータをInventoryFormに入れて返す
     */
    private InventoryForm makeInventoryForm(Inventory inventory) {
        InventoryForm inventoryForm = new InventoryForm();

        inventoryForm.setProductId(inventory.getProductsId());
        inventoryForm.setQuantity(inventory.getQuantity());
        inventoryForm.setReportDate(inventory.getReportDate().toLocalDate());

        return inventoryForm;
    }
}