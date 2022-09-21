package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Product;

public interface ProductService {

    List<Product> findAll();

   Optional<Product> getProduct(int id);

   void create(Product product);

   void update(Product product);


}
