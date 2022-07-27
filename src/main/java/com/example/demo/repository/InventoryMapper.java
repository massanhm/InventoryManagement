package com.example.demo.repository;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.Inventory;



@Mapper
public interface InventoryMapper {

//    @Select("SELECT inventorys.id, products_id, quantity, report_date, created_at, updated_at, name "
//           + "FROM inventorys "
//            + "INNER JOIN products ON inventorys.products_id = products.id "
//            + "WHERE report_date = #{sqlDate}")
    List<Inventory> findByDate(Date sqlDate);

}
