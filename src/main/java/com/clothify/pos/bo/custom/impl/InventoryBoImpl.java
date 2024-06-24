package com.clothify.pos.bo.custom.impl;

import com.clothify.pos.bo.custom.InventoryBo;
import com.clothify.pos.dao.DaoFactory;
import com.clothify.pos.dao.custom.InventoryDao;
import com.clothify.pos.dto.Inventory;
import com.clothify.pos.entity.InventoryEntity;
import com.clothify.pos.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;

public class InventoryBoImpl implements InventoryBo {

    private final InventoryDao inventoryDao = DaoFactory.getInstance().getDao(DaoType.INVENTORY);
    ModelMapper mapper = new ModelMapper();

    @Override
    public boolean persist(Inventory inventory) {
        return inventoryDao.persist(mapper.map(inventory, InventoryEntity.class));
    }

    @Override
    public boolean update(Inventory inventory) {
        return inventoryDao.update(mapper.map(inventory, InventoryEntity.class));
    }

    @Override
    public boolean delete(String id) {
        return inventoryDao.delete(id);
    }

    @Override
    public Inventory search(String id) {
        return mapper.map(inventoryDao.search(id), Inventory.class);
    }

    @Override
    public ObservableList<Inventory> searchAll() {
        ObservableList<InventoryEntity> inventoryEntities = inventoryDao.searchAll();
        ObservableList<Inventory> inventories = FXCollections.observableArrayList();
        inventoryEntities.forEach(inventoryEntity ->
                inventories.add(mapper.map(inventoryEntity, Inventory.class))
                );
        return inventories;
    }

    @Override
    public String getLatestId() {
        return inventoryDao.getLatestId();
    }

    @Override
    public Integer count() {
        return inventoryDao.count();
    }
}
