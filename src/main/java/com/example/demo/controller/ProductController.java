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

import com.example.demo.entity.Product;
import com.example.demo.form.ProductForm;
import com.example.demo.service.ProductService;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * 製品登録フォームを表示
     */
    @GetMapping("/form")
    public String form(
            ProductForm productForm,
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
    public String confirm(
            @Validated ProductForm productForm,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("title", "製品登録画面");
            return "product/form";
        }
        model.addAttribute("title", "確認画面");

        return "product/confirm";
    }

    /*
     * 商品情報の登録
     */
    @PostMapping("/complete")
    public String complete(
            @Validated @ModelAttribute ProductForm productForm,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        Product product = makeProduct(productForm, 0);

        if (result.hasErrors()) {
            model.addAttribute("productForm", productForm);
            model.addAttribute("title", "製品登録画面");

            return "product/form";
        }

        productService.create(product);
        redirectAttributes.addFlashAttribute("complete", "登録完了しました。");

        return "redirect:/product/list";
    }

    /**
     * 製品一覧を表示
     */
    @GetMapping("/list")
    public String showList(ProductForm productForm, Model model) {
        List<Product> list = productService.findAll();

        model.addAttribute("title", "製品一覧");
        model.addAttribute("list", list);

        return "product/list";
    }

    /**
     *一件データを取得し編集ページを表示する
     */
    @GetMapping("/{id}")
    public String edit(
            @PathVariable("id")  int id,
            ProductForm productForm,
            Model model) {
        Optional<Product> productOpt = productService.getProduct(id);

        Optional<ProductForm> productFromOpt = productOpt.map(t -> makeProductForm(t));

        if (productFromOpt.isPresent()) {
            productForm = productFromOpt.get();
        }

        model.addAttribute("productForm", productForm);
        model.addAttribute("id", id);
        model.addAttribute("title", "商品情報　編集画面");

        return "product/edit";
    }

    /*
     * 商品情報の更新
     */
    @PostMapping("/update")
    public String update(
        @RequestParam("id")  int id,
        @Validated @ModelAttribute ProductForm productForm,
        BindingResult result,
        Model model,
        RedirectAttributes redirectAttributes
        ) {

        if (result.hasErrors()) {
            model.addAttribute("title", "商品情報　編集画面");
            model.addAttribute("productForm", productForm);

            return "product/edit";
        }

        Product product = makeProduct(productForm, id);

        productService.update(product);
        redirectAttributes.addFlashAttribute("complete", "変更が完了しました");

        return "redirect:/product/list";

    }

    /*
     * ProductのデータをProductFormに入れて返す
     */
    private ProductForm makeProductForm(Product product) {
        ProductForm productForm = new ProductForm();

        productForm.setName(product.getName());

        return productForm;
    }

    private Product makeProduct(ProductForm productForm, int id) {
        Product product = new Product();

        product.setId(id);
        product.setName(productForm.getName());

        return product;
    }
}
