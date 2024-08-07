package com.clothify.pos.bo.custom;

import com.clothify.pos.bo.SuperBo;
import com.clothify.pos.dto.Inventory;
import javafx.collections.ObservableList;

public interface InventoryBo extends SuperBo {

    boolean persist(Inventory inventory);

    boolean update(Inventory inventory);

    boolean updateStock(String productId,Integer qty);

    int getInventoryOnHand(String productId);

    boolean delete(String id);

    Inventory search(String id);

    ObservableList<Inventory> searchAll();

    String getLatestId();

    long count();
}
