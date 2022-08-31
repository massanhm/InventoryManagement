package com.example.demo.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.Inventory;



@Mapper
public interface InventoryMapper {

    List<Inventory> findByDate(Date sqlDate);

    Optional<Inventory> findById(int id);

    void create(Inventory inventory);

    int update(Inventory inventory);

    int deleteById(int id);

}
