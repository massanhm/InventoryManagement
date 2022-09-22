package com.example.demo.form;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class InventoryForm {


    @NotNull(message ="商品名を選択してください。")
    private int productId;

    @NotNull(message = "数値を入力してください。")
    @PositiveOrZero(message = "正の数を入力してください。")
    private float quantity;

    @NotNull(message = "日付を設定してください。")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "日付が未来に設定されています。")
    private LocalDate reportDate;
}
