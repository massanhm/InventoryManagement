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

import com.example.demo.Form.ProductForm;
import com.example.demo.Service.ProductService;
import com.example.demo.entity.Product;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productservice;

    /**
     * 製品登録機能
     */
    @GetMapping("/form")
    public String form(ProductForm productForm,
            Model model,
            @ModelAttribute("complete") String complete) {
        model.addAttribute("title", "製品登録画面");
        return "product/form";
    }


    @PostMapping("/form")
    public String formGoBack(ProductForm productForm, Model model) {
        model.addAttribute("title", "製品登録画面");

        return "product/form";
    }


    @PostMapping("/confirm")
    public String confirm(@Validated ProductForm productForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("title", "製品登録画面");
            return "product/form";
        }
        model.addAttribute("title", "確認画面");
        return "product/confirm";
    }

    @PostMapping("/complete")
    public String complete(
            @Validated ProductForm productForm,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("title", "製品登録画面");
            return "product/form";
        }
        redirectAttributes.addFlashAttribute("complete", "登録完了しました。");
        return "redirect:/product/form";
    }


    /**
     * 製品一覧表示機能
     */
    @GetMapping("/list")
    public String showList(Model model) {
        List<Product> list = productservice.findAll();
        model.addAttribute("title", "製品一覧");
        model.addAttribute("productList", list);
        return "product/index";
    }

}
