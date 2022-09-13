package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {


    /*
     * アカウント登録画面を表示
     */
    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("title", "SignupForm");
        return "user/signup";
    }
}
