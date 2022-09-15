package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.Form.SignupForm;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {


    /*
     * アカウント登録画面を表示
     */
    @GetMapping("/signup")
    public String signup(SignupForm signupForm, Model model) {
        model.addAttribute("title", "SignupForm");
        return "user/signup";
    }
}
