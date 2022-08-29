package com.example.demo.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public void create(Inventory inventory) {
        inventoryMapper.create(inventory);
    }

    @Override
    public Optional<Inventory> getInventory(int id) {
        try {
            return inventoryMapper.findById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new InvNotFoundException("指定された在庫情報は存在しません");
        }
    }

    @Override
    public void update(Inventory inventory) {
        if (inventoryMapper.update(inventory) == 0) {
            throw new InvNotFoundException("更新する情報が存在しません");
        }

    }
//
//    @Override
//    public void deleteById(int id) {
//        if (inventoryMapper.deleteById(id) == 0) {
//            throw new InvNotFoundException("削除する情報が存在しません");
//        }
//    }

}
