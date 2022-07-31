package com.example.demo.repository;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.Inventory;



@Mapper
public interface InventoryMapper {

    List<Inventory> findByDate(Date sqlDate);

    void insert(Inventory inventory);

}
