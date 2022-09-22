package com.example.demo.config;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import com.example.demo.service.InvNotFoundException;
import com.example.demo.service.ProductNotFoundException;


@ControllerAdvice
public class WebMvcControllerAdvice {

    /*
     * This method changes empty character to null
     */
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public String handleException(ProductNotFoundException e,Model model) {
        model.addAttribute("message", e);
        return "error/CustomPage";
    }

    @ExceptionHandler(InvNotFoundException.class)
    public String handleException(InvNotFoundException e,Model model) {
        model.addAttribute("message", e);
        return "error/CustomPage";
    }
}
