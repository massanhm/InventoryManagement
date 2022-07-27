package com.example.demo.Service;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Inventory;
import com.example.demo.repository.InventoryMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryMapper inventoryMapper;

    @Override
    public List<Inventory> findByDate(Date sqlDate) {
        return inventoryMapper.findByDate(sqlDate);
    }

}
