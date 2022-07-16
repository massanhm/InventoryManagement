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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.Form.ProductForm;
import com.example.demo.Service.ProductService;
import com.example.demo.entity.Product;

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

    /*
     * 商品情報の登録
     */
    @PostMapping("/complete")
    public String complete(
            @Validated @ModelAttribute ProductForm productForm,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("title", "製品登録画面");

            return "product/form";
        }

        productService.create(productForm.getName());
        redirectAttributes.addFlashAttribute("complete", "登録完了しました。");

        return "redirect:/product/form";
    }

    /**
     * 製品一覧を表示
     */
    @GetMapping("/list")
    public String showList(Model model) {
        List<Product> list = productService.findAll();

        model.addAttribute("title", "製品一覧");
        model.addAttribute("productList", list);

        return "product/list";
    }

    /**
     *一件データを取得し編集ページを表示する
     */
    @GetMapping("/{product_id}/edit")
    public String edit(
            ProductForm productForm,
            @PathVariable("product_id") int product_id,
            Model model) {
        Optional<Product> productOpt = productService.findById(product_id);

        Optional<ProductForm> productFromOpt = productOpt.map(t -> makeProductForm(t));

        if (productFromOpt.isPresent()) {
            productForm = productFromOpt.get();
        }

        model.addAttribute("title", "商品情報　編集画面");
        model.addAttribute("product", productForm);

        return "product/edit";
    }

    /*
     * 商品情報の更新
     */
    @PostMapping("/update")
    public String update(
            @Validated @ModelAttribute ProductForm productForm,
            BindingResult result,
            @PathVariable("product_id") int product_id,
            Model model,
            RedirectAttributes redirectAttributes
            ) {

            if (result.hasErrors()) {
                model.addAttribute("title", "製品登録画面");

                return "product/edit";
            } else {
                return "";
            }
        }

    /*
     * ProductのデータをProductFormに入れて返す
     */
    private ProductForm makeProductForm(Product product) {
        ProductForm productForm = new ProductForm();

        productForm.setName(product.getName());

        return productForm;
    }
}
