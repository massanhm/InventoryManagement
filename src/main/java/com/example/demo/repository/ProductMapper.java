package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.Product;

@Mapper
public interface ProductMapper {

    @Select("SELECT * FROM products")
    List<Product> findAll();

    @Select("INSERT INTO products (name) values (#{name})")
    void insert(String name);

    @Select("SELECT * FROM products WHERE product_id = #{product_id}")
    Optional<Product> findById(int product_id);

    @Select("UPDATE products SET name = #{name} WHERE product_id = #{product_id}")
    int update(String name);
}
