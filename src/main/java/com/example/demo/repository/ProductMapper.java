package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.Product;

@Mapper
public interface ProductMapper {

    @Select("select * from products")
    List<Product> findAll();

//    @Select("SELECT * FROM product WHERE product_id; = #{product_id;}")
//    Product search(ProductSearchRequest product);
}
