package com.example.demo.Form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ProductForm {

    @NotNull(message = "製品名/単位を入力してください")
    @Size(min = 1, max = 100, message = "100文字以下で入力してください")
    private String name;
}
