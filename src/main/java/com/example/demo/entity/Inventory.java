package com.example.demo.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Inventory {

    private int id;

    private int productsId;

    private Product productsName;

    private float quantity;

    private Date reportDate;

    private LocalDateTime updatedAt;
}
