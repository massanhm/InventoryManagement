package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.entity.Product;

@Mapper
public interface ProductMapper {

    @Select("SELECT * FROM products")
    List<Product> findAll();

    @Insert("INSERT INTO products (name) values (#{name})")
    void insert(Product product);

    @Select("SELECT * FROM products WHERE id = #{id}")
    Optional<Product> findById(int product_id);

    @Update("UPDATE products SET name = #{name} WHERE id = #{id}")
    int update(Product product);
}
