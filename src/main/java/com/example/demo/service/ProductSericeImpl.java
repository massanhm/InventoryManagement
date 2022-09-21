package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductSericeImpl implements ProductService {

    private final ProductMapper productMapper;

    @Override
    public List<Product> findAll() {
        return productMapper.findAll();
    }

    @Override
    public Optional<Product> getProduct(int id) {
        try {
            return productMapper.findById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ProductNotFoundException("指定された商品は存在しません");
        }

    }

    @Override
    @Transactional
    public void create(Product product) {
        productMapper.insert(product);

    }

    @Override
    @Transactional
    public void update(Product product) {
        productMapper.update(product);
    }

}
