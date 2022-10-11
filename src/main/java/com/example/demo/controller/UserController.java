package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.form.SignupForm;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /*
     * アカウント登録画面を表示
     */
    @GetMapping("/signup")
    public String signup(SignupForm signupForm, Model model) {
        model.addAttribute("title", "ユーザー登録画面");
        return "user/signup";
    }

    /*
     * アカウント作成処理
     */
    @PostMapping("/usercreate")
    public String create(
            @Validated @ModelAttribute SignupForm signupForm,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
            ) {

        if (result.hasErrors()) {
            model.addAttribute("signupForm", signupForm);
            model.addAttribute("title", "ユーザー登録画面");

            return "user/signup";
        }

        userService.create(signupForm.getUsername(), signupForm.getPassword());
        redirectAttributes.addFlashAttribute("complete", "ユーザー登録が完了しました。");

        return "redirect:/signup";
    }
}
