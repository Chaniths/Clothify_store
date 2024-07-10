package com.clothify.pos.bo.custom.impl;

import com.clothify.pos.bo.custom.InventoryBo;
import com.clothify.pos.dao.DaoFactory;
import com.clothify.pos.dao.custom.InventoryDao;
import com.clothify.pos.dto.Inventory;
import com.clothify.pos.entity.InventoryEntity;
import com.clothify.pos.util.DaoType;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InventoryBoImpl implements InventoryBo {

    private final InventoryDao inventoryDao = DaoFactory.getInstance().getDao(DaoType.INVENTORY);
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public boolean persist(Inventory inventory) {
        return inventoryDao.persist(mapper.convertValue(inventory, InventoryEntity.class));
    }

    @Override
    public boolean update(Inventory inventory) {
        return inventoryDao.update(mapper.convertValue(inventory, InventoryEntity.class));
    }

   @Override
    public boolean updateStock(String productId, Integer qty) {
       return inventoryDao.updateStock(productId,qty);
   }

    @Override
    public int getInventoryOnHand(String productId) {
        return inventoryDao.getInventoryOnHand(productId);
    }

    @Override
    public boolean delete(String id) {
        return inventoryDao.delete(id);
    }

    @Override
    public Inventory search(String id) {
        return mapper.convertValue(inventoryDao.search(id), Inventory.class);
    }

    @Override
    public ObservableList<Inventory> searchAll() {
        ObservableList<InventoryEntity> inventoryEntities = inventoryDao.searchAll();
        ObservableList<Inventory> inventories = FXCollections.observableArrayList();
        inventoryEntities.forEach(inventoryEntity ->
                inventories.add(mapper.convertValue(inventoryEntity, Inventory.class))
                );
        return inventories;
    }

    @Override
    public String getLatestId() {
        return inventoryDao.getLatestId();
    }

    @Override
    public long count() {
        return inventoryDao.count();
    }
}
