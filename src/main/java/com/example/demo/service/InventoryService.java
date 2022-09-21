package com.example.demo.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Inventory;

public interface InventoryService {

    List<Inventory> findByDate(Date sqlDate);

    Optional<Inventory> getInventory(int id);

    void create(Inventory inventory);

    void update(Inventory inventory);

    void deleteById(int id);
}
