package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductMapper;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;

    public List<Product> findAll() {
        return productMapper.findAll();
    }

   public Optional<Product> findById(int product_id) {
       return productMapper.findById(product_id);
   }

   @Transactional
   public void create(String name) {
       productMapper.insert(name);
   }

   @Transactional
   public void update(String name) {
       productMapper.update(name);
   }


}
