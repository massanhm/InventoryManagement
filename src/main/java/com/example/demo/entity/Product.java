package com.example.demo.entity;

import java.util.List;

import groovy.transform.ToString;
import lombok.Data;

@Data
@ToString(excludes = {"inventorys"})
public class Product {

    private int id;

    private String name;

    private List<Inventory> inventorys;
}
