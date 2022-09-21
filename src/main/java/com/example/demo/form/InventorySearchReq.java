package com.example.demo.Form;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class InventorySearchReq{

    @NotNull (message = "日付を設定してください。")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "日付が未来に設定されています。")
    private LocalDate reportDate;

}
