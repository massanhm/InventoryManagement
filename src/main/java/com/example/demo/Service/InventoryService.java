package com.example.demo.Service;

import java.sql.Date;
import java.util.List;

import com.example.demo.entity.Inventory;

public interface InventoryService {

    List<Inventory> findByDate(Date sqlDate);
}
