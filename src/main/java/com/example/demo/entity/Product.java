package com.example.demo.entity;

import java.util.List;

import lombok.Data;

@Data
public class Product {

    private int id;

    private String name;

    private List<Inventory> inventorys;
}
