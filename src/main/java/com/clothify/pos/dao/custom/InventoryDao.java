package com.clothify.pos.dao.custom;

import com.clothify.pos.dao.SuperDao;
import com.clothify.pos.entity.InventoryEntity;
import javafx.collections.ObservableList;

public interface InventoryDao extends SuperDao {

    boolean persist(InventoryEntity entity);

    boolean update(InventoryEntity entity);

    boolean updateStock(String productId,Integer qty);

    int getInventoryOnHand(String productId);

    boolean delete(String id);

    InventoryEntity search(String id);

    ObservableList<InventoryEntity> searchAll();

    String getLatestId();

    long count();

}
